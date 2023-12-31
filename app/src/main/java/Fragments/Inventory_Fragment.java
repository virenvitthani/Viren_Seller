package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new_viren_seller.R;
import com.example.new_viren_seller.Splash_Images;
import com.example.new_viren_seller.ViewproAdapter;

import DataBase.InstanceClass;
import DataBase.TransferDataFragment;
import DataBase.ViewproductData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inventory_Fragment extends Fragment {

    ViewproAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.inventory_fragment,container,false);

        InstanceClass.CallApi().viewProduct(Splash_Images.preferences.getInt("sellerid", 0)).enqueue(new Callback<ViewproductData>() {
            @Override
            public void onResponse(Call<ViewproductData> call, Response<ViewproductData> response) {
                if (response.body().getConnection() == 1) {
                    if (response.body().getResult() == 1) {
                        adapter=new ViewproAdapter(Inventory_Fragment.this, response.body().getProductdata(), new TransferDataFragment() {
                            @Override
                            public void getDataFromFragment(int id, String name, String price, String description, String imgeData) {
                                Add_Product_Fragment add_product_fragment=new Add_Product_Fragment();
                                Bundle args = new Bundle();
                                args.putString("id", String.valueOf(id));
                                args.putString("name", name);
                                args.putString("price", price);
                                args.putString("descripation",description);
                                args.putString("img",imgeData);
                                add_product_fragment.setArguments(args);

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.replace(R.id.framlayout, add_product_fragment);
                                transaction.commit();
                            }
                        });
                        recyclerView=view.findViewById(R.id.recycleview);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        manager.setOrientation(RecyclerView.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        Log.d("TTT", "onResponse: connection"+response.body().getConnection());
//                                    Toast.makeText(getContext(), "Product Add Sucessfully", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("TTT", "onResponse: fail connection"+response.body().getConnection());
//                                    Toast.makeText(getContext(), "Failed to Add Product", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("TTT", "onResponse: somthing went wrong getconnection"+response.body().getConnection());
//                                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ViewproductData> call, Throwable t) {

            }
        });
        return view;
    }
}
