package com.opencabinet.models.drawer;

import com.opencabinet.models.item.ItemCore;
import com.opencabinet.models.item.ItemPointer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrawerPointerItems extends DrawerCore{
    private List<ItemPointer> itemPointers = new ArrayList<ItemPointer>();
    public DrawerPointerItems(String ownerName, String drawerName, int layout, int privacy) {
        super(ownerName, drawerName, layout, privacy);
    }

    public DrawerPointerItems(String ownerName, String drawerName, int layout, int privacy, List<ItemPointer> itemPointers) {
        super(ownerName, drawerName, layout, privacy);
        this.itemPointers = itemPointers;
    }

    public String encodeToJson(){
        JSONObject drawerJson = new JSONObject();
        drawerJson.put("ownerName", ownerName);
        drawerJson.put("drawerName", drawerName);
        drawerJson.put("layout", layout);
        drawerJson.put("privacy", privacy);

        JSONArray itemsJson = new JSONArray();
        for(ItemPointer i : itemPointers){
            JSONObject itemJson = new JSONObject();
            itemJson.put("itemName", i.getName());
            itemJson.put("layout", i.getLayout());
            itemJson.put("pointer", i.getPointer());
        }
        drawerJson.put("items", itemsJson);
        return drawerJson.toJSONString();
    }

    public List<ItemPointer> getItemPointers() {
        return itemPointers;
    }

    public void addItemPointer(ItemPointer itemPointer){
        itemPointers.add(itemPointer);
    }

    public void setItemPointers(List<ItemPointer> itemPointers) {
        this.itemPointers = itemPointers;
    }

    public String getItemPointersJson(){
        List<String> itemJsons = new ArrayList<>();

        for(ItemPointer i : itemPointers){
            itemJsons.add(i.encodeToJson());
        }
        JSONObject itemsJson = new JSONObject();
        JSONArray itemsJsonArray = new JSONArray();
        for(String i : itemJsons){
            itemsJsonArray.add(i);
        }
        itemsJson.put("items", itemsJsonArray);
        return itemsJson.toJSONString();
    }
}
