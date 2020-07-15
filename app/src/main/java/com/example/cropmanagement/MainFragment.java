package com.example.cropmanagement;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cropmanagement.coldstoragefragments.ColdStorage;
import com.example.cropmanagement.diseasesfragment.DiseasesFragment;
import com.example.cropmanagement.soilfragments.SoilFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ImageView soilimage,diseasesimage,coldimage;
    private TextView header_name;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        final TextView header_name=(TextView)view.findViewById(R.id.header_name);
        final TextView h_name=view.findViewById(R.id.h_name);
        soilimage=view.findViewById(R.id.soilimage);
        diseasesimage=view.findViewById(R.id.diseasesimage);
        coldimage=view.findViewById(R.id.coldimage);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference(auth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                //header_name.setText("Welcome Mr/Mrs."+userProfile.getName());
                h_name.setText("Welcome Mr/Mrs."+userProfile.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       soilimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(),SoilActivity.class));
               fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment,new SoilFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();



            }

        });
       diseasesimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment,new DiseasesFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        coldimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment,new ColdStorage());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();}
        });



        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(getActivity(), Login.class));
               default:
                   return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
         inflater.inflate(R.menu.menu,menu);
         }
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.hometitle);
    }
}
