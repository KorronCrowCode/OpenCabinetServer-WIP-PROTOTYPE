package com.opencabinet.models.item;

import org.json.simple.JSONObject;

public class ItemCore {
    protected String itemName;
    protected int layout;

    public ItemCore(String itemName, int layout) {
        this.itemName = itemName;
        this.layout = layout;
    }

    public ItemCore() {}

    public String getName() {
        return itemName;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public String encodeToJson(){
        JSONObject itemJson = new JSONObject();
        itemJson.put("itemName", itemName);
        itemJson.put("layout", layout);

        return itemJson.toJSONString();
    }


}
