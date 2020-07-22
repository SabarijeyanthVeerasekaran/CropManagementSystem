package com.example.cropmanagement.soilfragments.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class FileInfo1 {



@SerializedName("response")
private String response;

    public String getResponse() {
        return response;
    }

    private String name;
        private long fileSize;
        //private JSONObject response=new JSONObject();
   //     private String response;
    /*public JSONObject getResponse() {
        return response;
    }

   /* public void setResponse(JSONObject response) {
        this.response = response;
    }

    */



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}