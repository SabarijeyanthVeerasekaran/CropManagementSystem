package com.example.cropmanagement.soilfragments;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cropmanagement.R;
import com.example.cropmanagement.diseasesfragment.Upload;
import com.example.cropmanagement.diseasesfragment.retrofit.model.FileInfo;
import com.example.cropmanagement.diseasesfragment.retrofit.remote.ApiUtils;
import com.example.cropmanagement.soilfragments.retrofit.model.FileInfo1;
import com.example.cropmanagement.soilfragments.retrofit.remote.ApiUtils1;
import com.example.cropmanagement.soilfragments.retrofit.remote.FileService1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckSoil extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout soilcontainerfragment;
    private Button selectbtn,uploadbtn;
    private ImageView checkimage;
    private TextView diseasStatus;
    private Bitmap bitmap;
    private String imagepath;
    private ProgressBar progressBar;
    private Uri imageUri;
    private StorageReference storageReference;
    private FileService1 fileService;
    private String message;
    private static  int count=0;
    private StorageTask storageTask;
    public CheckSoil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_check_soil, container, false);
        //soilcontainerfragment=view.findViewById(R.id.soilcontainerFragment);
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.soilcontainerFragment,new RedSoil());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        checkimage=(ImageView)view.findViewById(R.id.checkimage);
        selectbtn=(Button)view.findViewById(R.id.checkselectbtn);
        uploadbtn=(Button)view.findViewById(R.id.checkuploadbtn);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        diseasStatus=(TextView)view.findViewById(R.id.diseasesstatus);

        fileService= ApiUtils1.getFileServices();
        //fileService= ApiUtils.getFileServices();
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadbtn.setEnabled(true);
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }
        });
        storageReference= FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().getUid());
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(1);

                final File file=new File(imagepath);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                MultipartBody.Part body=MultipartBody.Part.createFormData("file",file.getName(),requestBody);

                Call<FileInfo1> call=fileService.upload(body);
                call.enqueue(new Callback<FileInfo1>() {
                    @Override
                    public void onResponse(Call<FileInfo1> call, Response<FileInfo1> response) {
                        FileInfo1 fileInfo=response.body();
                        if(response.isSuccessful())
                        {

                            Toast.makeText(getActivity(),""+ response.toString(),Toast.LENGTH_LONG).show();


                        }
                        if(!response.isSuccessful())
                        {

                            Toast.makeText(getActivity(),"No Diseases Detected",Toast.LENGTH_LONG).show();


                        }
                    }

                    @Override
                    public void onFailure(Call<FileInfo1> call, Throwable t) {
                        message=t.getMessage().trim().substring(34);
                        //diseasStatus.setText("The Plant was affected by"+message);
                        Toast.makeText(getActivity(),"The soil  quality is "+message, Toast.LENGTH_LONG).show();
                    }
                });

                if(storageTask!=null && storageTask.isInProgress())
                    Toast.makeText(getActivity(),"Uploading in progess...",Toast.LENGTH_SHORT).show();
                else
                    uploadImage();


            }

        });

        return view;
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver=getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage() {
        if(imageUri!=null)
        {
            StorageReference fileReference=storageReference.child("uploads/"+System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));
            FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid()).child("uploads/").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    count=(int)dataSnapshot.getChildrenCount();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            storageTask=fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(),"Uploaded Sucessful",Toast.LENGTH_SHORT).show();

                            Upload uploadImage=new Upload(message,taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getUid())
                                    .child("uploads/").child(String.valueOf(count)).setValue(uploadImage);

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
                            progressBar.setProgress((int)progress);
                            //progressStatus.setText((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(getActivity(),"No file selected",Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getActivity(), "Unable to choose file", Toast.LENGTH_LONG).show();
                return;
            }
            imageUri = data.getData();
            imagepath = getPathFromUri(imageUri);
            /*try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            d_diseasesimage.setImageBitmap(bitmap);


             */
            Picasso.with(getActivity()).load(imageUri).into(checkimage);
        }
    }
    private String getPathFromUri(Uri uri)
    {
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getActivity().getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity=(AppCompatActivity)getActivity();
        ActionBar actionBar=appCompatActivity.getSupportActionBar();
        actionBar.setTitle(R.string.soilqualitytitle);
    }






    }


