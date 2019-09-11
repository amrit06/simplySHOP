package com.example.simplyshop;

public class spinnerDataModel {
    private String name;
    private int imgid;

    public spinnerDataModel(String label, int id){
        this.name = label;
        this.imgid = id;
    }

    public String getLabel(){
        return name;
    }

    public int getImgId(){
        return imgid;
    }

}
