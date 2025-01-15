package com.opencabinet.db.drawer;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.drawer.DrawerCoreItems;
import com.opencabinet.models.drawer.DrawerPointerItems;
import com.opencabinet.models.item.ItemCore;
import com.opencabinet.models.item.ItemPointer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DrawerPacker {

    public DrawerPacker() {}

    protected DrawerCore unpackDrawerCore(JSONObject drawer){
        DrawerCore unpackDrawerCore = null;


        String owner = (String) drawer.get("ownerName");
        String name = (String) drawer.get("drawerName");
        int layout = (Integer) Integer.valueOf((String) drawer.get("layout"));
        int privacy = (Integer) Integer.valueOf((String) drawer.get("privacy"));

        return unpackDrawerCore = new DrawerCore(owner, name, layout, privacy);

    }

    protected DrawerPointerItems unpackDrawerPointerItems(JSONObject drawer){
        DrawerPointerItems drawerPointerItems = null;

        String owner = (String) drawer.get("ownerName");
        String name = (String) drawer.get("drawerName");
        int layout = (Integer) Integer.valueOf((String) drawer.get("layout"));
        int privacy = (Integer) Integer.valueOf((String) drawer.get("privacy"));

        List<ItemPointer> itemPointers = new ArrayList<>();
        for(Object o : (JSONArray) drawer.get("items")){
            JSONObject item = (JSONObject) o;
            String itemName = (String) item.get("itemName");
            int itemLayout = (Integer) Integer.valueOf((String) drawer.get("layout"));
            String pointer = (String) item.get("pointer");
            ItemPointer itemPointer = new ItemPointer(itemName, itemLayout, pointer);
            itemPointers.add(itemPointer);
        }
        return drawerPointerItems = new DrawerPointerItems(owner, name, layout, privacy, itemPointers);

    }

    protected DrawerCoreItems unpackDrawerCoreItems(JSONObject drawer){
        DrawerCoreItems unpackedDrawerCoreItems = null;

        String owner = (String) drawer.get("ownerName");
        String name = (String) drawer.get("drawerName");
        int layout = (Integer) Integer.valueOf((String) drawer.get("layout"));
        int privacy = (Integer) Integer.valueOf((String) drawer.get("privacy"));
        List<ItemCore> items = new ArrayList<>();
        for(Object o : (JSONArray) drawer.get("items")){
            JSONObject item = (JSONObject) o;
            String itemName = (String) item.get("itemName");
            int itemLayout = (Integer) Integer.valueOf((String) drawer.get("layout"));
            ItemCore itemFull = new ItemCore(itemName, itemLayout);
            items.add(itemFull);
        }
        return unpackedDrawerCoreItems = new DrawerCoreItems(owner, name, layout, privacy, items);
    }

}
