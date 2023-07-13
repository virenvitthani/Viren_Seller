package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new_viren_seller.R;
import com.example.new_viren_seller.ViewproAdapter;

public class Inventory_Fragment extends Fragment {

    ViewproAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.inventory_fragment,container,false);

        adapter=new ViewproAdapter(Inventory_Fragment.this);
        recyclerView=view.findViewById(R.id.recycleview);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
