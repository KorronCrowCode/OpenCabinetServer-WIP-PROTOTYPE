package com.opencabinet.models.drawer;

import com.opencabinet.models.item.ItemCore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrawerCoreItems extends DrawerCore {
    private List<ItemCore> itemCores = new ArrayList<ItemCore>();

    public DrawerCoreItems(String ownerName, String drawerName, int layout, int privacy) {
        super(ownerName, drawerName, layout, privacy);
    }

    public DrawerCoreItems(String ownerName, String drawerName, int layout, int privacy, List<ItemCore> itemCores) {
        super(ownerName, drawerName, layout, privacy);
        this.itemCores = itemCores;
    }


    public String encodeToJson(){
        JSONObject drawerJson = new JSONObject();
        drawerJson.put("ownerName", ownerName);
        drawerJson.put("drawerName", drawerName);
        drawerJson.put("layout", layout);
        drawerJson.put("privacy", privacy);

        JSONArray itemsJson = new JSONArray();
        for(ItemCore i : itemCores){
            JSONObject itemJson = new JSONObject();
            itemJson.put("itemName", i.getName());
            itemJson.put("layout", i.getLayout());
        }
        drawerJson.put("items", itemsJson);
        return drawerJson.toJSONString();
    }

    public String getName() {
        return drawerName;
    }

    public int getLayout() {
        return layout;
    }

    public int getPrivacy() {
        return privacy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<ItemCore> getItemCores() {
        return itemCores;
    }

    public void setItemCores(List<ItemCore> itemCores) {
        this.itemCores = itemCores;
    }
}
