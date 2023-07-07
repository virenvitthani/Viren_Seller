package com.example.new_viren_seller;

import static com.example.new_viren_seller.Splash_Images.editor;
import static com.example.new_viren_seller.Splash_Images.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.new_viren_seller.databinding.HomePageBinding;
import com.google.android.material.navigation.NavigationView;

import Fragments.Add_Product_Fragment;
import Fragments.Home_Fragment;
import Fragments.Inventory_Fragment;

public class Main_Page extends AppCompatActivity
{
    HomePageBinding binding;
    TextView headername,headeremail;
    ImageView headerimage;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        binding=HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addfragment(new Home_Fragment());

        View headerview = binding.navigationView.getHeaderView(0);
        headerimage = headerview.findViewById(R.id.headerimage);
        headername = headerview.findViewById(R.id.headername);
        headeremail = headerview.findViewById(R.id.headeremail);

        headername.setText(preferences.getString("sellername",null));
        headeremail.setText(preferences.getString("selleremail",null));


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Main_Page.this,binding.drawerlayout,binding.appbarMain.toolbar,R.string.open,R.string.close);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home){
                    addfragment(new Home_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.addproduct){
                    addfragment(new Add_Product_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.inventory){
                    addfragment(new Inventory_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.signout){
                    editor.putInt("login",0);
                    editor.commit();
                    Intent intent = new Intent(Main_Page.this,Sing_Up_Page.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }
}
