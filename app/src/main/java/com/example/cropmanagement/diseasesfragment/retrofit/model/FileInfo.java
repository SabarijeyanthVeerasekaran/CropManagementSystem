package com.example.cropmanagement.diseasesfragment.retrofit.model;

import org.json.JSONObject;

public class FileInfo {

        private String name;
        private long fileSize;
        private JSONObject response=new JSONObject();

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

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