package com.opencabinet.models.item;

import org.json.simple.JSONObject;

public class ItemPointer extends ItemCore{
    String pointer;

    public ItemPointer(String itemName, int layout, String pointer) {
        super(itemName, layout);
        this.pointer = pointer;
    }

    public String getPointer() {
        return pointer;
    }

    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public String encodeToJson(){
        JSONObject itemJson = new JSONObject();
        itemJson.put("itemName", itemName);
        itemJson.put("layout", layout);
        itemJson.put("pointer", pointer);

        return itemJson.toJSONString();
    }
}
