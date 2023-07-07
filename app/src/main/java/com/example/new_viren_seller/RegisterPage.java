package com.example.new_viren_seller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.new_viren_seller.databinding.ActivityRegisterPageBinding;
import DataBase.InstanceClass;
import DataBase.Register_Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {

    ActivityRegisterPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("1234");
                InstanceClass.CallApi().registerUser(binding.name.getText().toString(),binding.email.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<Register_Data>() {

                    @Override
                    public void onResponse(Call<Register_Data> call, Response<Register_Data> response) {
                        if(response.body().getConnection()==1){
                            if(response.body().getResult()==1){
                                Log.d("TTT", "onResponse: "+response.body().getConnection());
                                Toast.makeText(RegisterPage.this,"Register",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterPage.this,Sing_Up_Page.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterPage.this,"Failed register",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterPage.this,"Somthing Went Wrong",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Register_Data> call, Throwable t) {
                        Log.e("TTT", "onFailure: "+t.getLocalizedMessage() );
                    }
                });
            }
        });
    }
}