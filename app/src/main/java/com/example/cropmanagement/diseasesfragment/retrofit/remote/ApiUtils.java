package com.example.cropmanagement.diseasesfragment.retrofit.remote;

public class ApiUtils {
    private ApiUtils() {
    }
    public static final String Url="http://192.168.43.182:5000/";

    public static FileService getFileServices()
    {
        return RetrofitClient.getClient(Url).create(FileService.class);
    }
}
