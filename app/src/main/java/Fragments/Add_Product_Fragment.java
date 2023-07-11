package Fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.new_viren_seller.R;
import com.example.new_viren_seller.Splash_Images;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import DataBase.AddproductData;
import DataBase.InstanceClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment
{

    EditText pdname,pdprice,pddescription;
    Button submit;
    ImageView addcontactsImg;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_product,container,false);
        addcontactsImg = view.findViewById(R.id.addcontactsImg);
        pdname = view.findViewById(R.id.pdname);
        pdprice = view.findViewById(R.id.pdprice);
        pddescription = view.findViewById(R.id.pddescription);

        submit = view.findViewById(R.id.submit);

        addcontactsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagechooser();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bm = ((BitmapDrawable) addcontactsImg.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 40, baos); // bm is the bitmap object
                byte[] b = baos.toByteArray();

                String imagedata = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imagedata = Base64.getEncoder().encodeToString(b);
                }

                addfragment(new Home_Fragment());

                if(Splash_Images.preferences.getString("from",null).equals("add")) {
                    InstanceClass.CallApi().addproduct(Splash_Images.preferences.getInt("userid", 0), pdname.getText().toString(), pdprice.getText(), pddescription.getText().toString(), imagedata).enqueue(new Callback<AddproductData>() {
                        @Override
                        public void onResponse(Call<AddproductData> call, Response<AddproductData> response) {
                            if (response.body().getConnection() == 1) {
                                if (response.body().getProductaddd() == 1) {
                                    Toast.makeText(getContext(), "Product Add Sucessfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "Failed to Add Product", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddproductData> call, Throwable t) {

                        }
                    });
                }
            }
        });
        return view;
    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }

    void imagechooser()
    {
        CropImage.activity()
                .start(getContext(),this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                addcontactsImg.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
