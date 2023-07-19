package com.example.new_viren_seller;

import static com.example.new_viren_seller.Splash_Images.editor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.new_viren_seller.databinding.ActivitySingUpPageBinding;
import DataBase.InstanceClass;
import DataBase.LoginData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sing_Up_Page extends AppCompatActivity {

    ActivitySingUpPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstanceClass.CallApi().loginuser(binding.email.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(response.body().getConnection()==1){
                            if(response.body().getResult()==1){
                                Intent intent = new Intent(Sing_Up_Page.this,Main_Page.class);
                                editor.putInt("login",1);
//                                editor.putInt("sellerid", Integer.parseInt(response.body().getUserdata().getId()));
//                                editor.putString("sellername",response.body().getUserdata().getName());
//                                editor.putString("selleremail",response.body().getUserdata().getEmail());
                                editor.commit();
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(Sing_Up_Page.this, RegisterPage.class);
                                startActivity(intent);
                                Toast.makeText(Sing_Up_Page.this,"Failed Loging",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(Sing_Up_Page.this,"Somthing Went Wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Log.e("TTT", "onFailure: "+t.getLocalizedMessage() );
                    }
                });
            }
        });
    }
}