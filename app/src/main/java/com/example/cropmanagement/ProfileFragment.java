package com.example.cropmanagement;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cropmanagement.diseasesfragment.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLock;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView p_name,p_district,p_email;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ImageView profileimage;
    File localFile;
    private Uri imageUri;
    private String imagepath;
    private ProgressBar mprogressbar;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        p_name=view.findViewById(R.id.p_name);
        p_email=view.findViewById(R.id.p_email);
        p_district=view.findViewById(R.id.p_district);
        profileimage=view.findViewById(R.id.profileimage);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
                if(imageUri!=null)
                {
                    StorageReference fileReference= FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("profilepicture");


                    fileReference.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    //mprogressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity(),"Uploaded Sucessful",Toast.LENGTH_SHORT).show();


                                }


                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                    // progressBar.setProgress((int)progress);
                                    //progressStatus.setText((int) progress);
                                }
                            });
                }
                else {
                    Toast.makeText(getActivity(),"No file selected",Toast.LENGTH_SHORT).show();
                }


            }
        });



        auth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference(auth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                p_name.setText(userProfile.getName());
                p_email.setText(userProfile.getEmail());
                p_district.setText(userProfile.getDistrict());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void chooseFile() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);

    }

    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.profiletitle);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getActivity(), "Unable to choose file", Toast.LENGTH_LONG).show();
                return;
            }
            imageUri = data.getData();
            //imagepath = getPathFromUri(imageUri);
            /*try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            d_diseasesimage.setImageBitmap(bitmap);


             */
            Picasso.with(getActivity()).load(imageUri).into(profileimage);



        }}
    }
