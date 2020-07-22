package com.example.cropmanagement.soilfragments.retrofit.remote;


import com.example.cropmanagement.soilfragments.retrofit.model.FileInfo1;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService1 {

    @Multipart
    @POST("/api/upload")
    Call<FileInfo1> upload(@Part MultipartBody.Part file);
}
