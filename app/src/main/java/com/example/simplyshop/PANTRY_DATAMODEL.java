package com.example.simplyshop;

import java.util.ArrayList;

public class PANTRY_DATAMODEL {
    String list;
    int imgId;
    Boolean checked;

    PANTRY_DATAMODEL(String itemInfo, int imgid, Boolean check ){
    this.list = itemInfo;
    this.imgId = imgid;
    this.checked = check;
    }

}
