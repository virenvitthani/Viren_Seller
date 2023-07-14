package com.example.new_viren_seller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import DataBase.Productdatum;
import Fragments.Inventory_Fragment;

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
    public void onBindViewHolder(@NonNull ViewproAdapter.proHolder holder, int position) {
        holder.pdname.setText("\t"+productdata.get(position).getPdname());
        holder.pdprice.setText("pdprice"+productdata.get(position).getPdprice());
        holder.pddescription.setText("pddescription"+productdata.get(position).getDescription());

        Glide.with(inventory_fragment).load("https://mihirecommarce.000webhostapp.com/Mysite"+productdata.get(position).getImages()).into(holder.imageView);

        holder.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Popup",Toast.LENGTH_LONG).show();
                PopupMenu popupMenu = new PopupMenu(inventory_fragment.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
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
