package com.example.new_viren_seller;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import DataBase.InstanceClass;
import DataBase.Productdatum;
import DataBase.Register_Data;
import Fragments.Add_Product_Fragment;
import Fragments.Inventory_Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewproAdapter extends RecyclerView.Adapter<ViewproAdapter.proHolder> {

    Inventory_Fragment inventory_fragment;
    List<Productdatum> productdata;

    public ViewproAdapter(Inventory_Fragment inventory_fragment, List<Productdatum> productdata) {
        this.inventory_fragment = inventory_fragment;
        this.productdata = productdata;
    }

    @NonNull
    @Override
    public ViewproAdapter.proHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_inventory_item,parent,false);
        proHolder holder = new proHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewproAdapter.proHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pdname.setText("Name :"+productdata.get(position).getPdname());
        holder.pdprice.setText("Price :"+productdata.get(position).getPdprice());
        holder.pddescription.setText("Des : "+productdata.get(position).getDescription());

        Glide.with(inventory_fragment).load("https://mihirecommarce.000webhostapp.com/Mysite/"+productdata.get(position).getImages()).into(holder.imageView);


        holder.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Popup",Toast.LENGTH_LONG).show();
                PopupMenu popupMenu = new PopupMenu(inventory_fragment.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.update)
                        {
                            addfragment(new Add_Product_Fragment());


                        }
                        if (item.getItemId()==R.id.delete)
                        {
                            InstanceClass.CallApi().deleteproduct(Integer.parseInt(productdata.get(position).getId())).enqueue(new Callback<Register_Data>() {
                                @Override
                                public void onResponse(Call<Register_Data> call, Response<Register_Data> response) {
                                    if (response.body().getConnection()==1) {
                                        if (response.body().getResult()==1) {
                                            Toast.makeText(inventory_fragment.getContext(),"Product Delete",Toast.LENGTH_LONG).show();
                                            productdata.remove(position);
                                            notifyDataSetChanged();
                                        }
                                        else
                                        {
                                            Toast.makeText(inventory_fragment.getContext(),"Delete Fail",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Register_Data> call, Throwable t) {

                                }
                            });
                        }
                        return true;
                    }
                });popupMenu.show();

            }
        });
    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = inventory_fragment.getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class proHolder extends RecyclerView.ViewHolder {
        ImageView imageView,menuoption;
        TextView pdname,pdprice,pddescription;

        public proHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.inven_recyc_img);
            menuoption = itemView.findViewById(R.id.inven_recyc_menuoption);
            pdname = itemView.findViewById(R.id.inven_recyc_name);
            pdprice = itemView.findViewById(R.id.inven_recyc_price);
            pddescription = itemView.findViewById(R.id.inven_recyc_description);
        }
    }
}
