package com.its.bigstarsapp.Controllers;

public class GlobalVariable {
    String urlData;
    String urlUpload;

    String ipAddress = "http://bigstartsjember.my.id/";

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
