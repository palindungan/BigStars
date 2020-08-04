package com.its.bigstarsapp.Controllers;

public class GlobalVariable {
    String urlData;
    String urlUpload;

    String ipAddress = "http://192.168.137.1/BigStars/";

    public GlobalVariable() {
        urlData = ipAddress + "web/api/";
        urlUpload = ipAddress + "web/upload/";
    }

    public String getUrlData() {
        return urlData;
    }

    public String getUrlUpload() {
        return urlUpload;
    }
}
