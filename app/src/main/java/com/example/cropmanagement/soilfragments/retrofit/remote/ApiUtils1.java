package com.example.cropmanagement.soilfragments.retrofit.remote;

public class ApiUtils1 {
    private ApiUtils1() {
    }
    public static final String Url="http://192.168.1.11:5001/";

    public static FileService1 getFileServices()
    {
        return RetrofitClient1.getClient(Url).create(FileService1.class);
    }
}
