package com.example.cropmanagement;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cropmanagement.diseasesfragment.Upload;
import com.google.firebase.auth.FirebaseAuth;
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
public class GalleryFragment extends Fragment {


    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private DatabaseReference databaseReference;

    private List<Upload> mupload;


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView=view.findViewById(R.id.d_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mupload=new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid())
                .child("uploads/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {
                    Upload upload=postSnapShot.getValue(Upload.class);
                    mupload.add(upload);
                }
                imageAdapter=new ImageAdapter(getActivity(),mupload);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.gallerytitle);
    }
}
