package com.example.cropmanagement.coldstoragefragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cropmanagement.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColdStorage extends Fragment  {

    private TextView StorageEnvironment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Spinner spinner;
    private Button serachStorage;
    private String text;


    public ColdStorage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cold_storage, container, false);
        StorageEnvironment=(TextView)view.findViewById(R.id.storageenvironment);
        StorageEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment,new StorageEnvironmentFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        spinner=(Spinner)view.findViewById(R.id.spinner);
        serachStorage=view.findViewById(R.id.searchStorage);


      final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.districts,android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              text=parent.getItemAtPosition(position).toString();



          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });
      serachStorage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getActivity(),"Searching....",Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putString("districtname",text);
           districtFragment districtFragment1=new districtFragment();
           districtFragment1.setArguments(bundle);
           getFragmentManager().beginTransaction().replace(R.id.containerFragment,districtFragment1).addToBackStack(null).commit();

          }
      });











        return view;
    }
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.coldstorage);
    }


}
