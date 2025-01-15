package com.opencabinet.models.item;

import com.opencabinet.Consts.CustomEnums;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemPackage extends ItemCore{
    String data;
    public ItemPackage(String itemName, int layout, String data) {
        super(itemName, layout);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String encodeToJson(){
        JSONObject itemJson = new JSONObject();
        itemJson.put("itemName", itemName);
        itemJson.put("layout", layout);
        itemJson.put("data", data);

        return itemJson.toJSONString();
    }
}
