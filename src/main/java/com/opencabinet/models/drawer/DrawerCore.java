package com.opencabinet.models.drawer;

import com.opencabinet.models.item.ItemCore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DrawerCore {

    protected String ownerName;
    protected String drawerName;
    protected int layout;
    protected int privacy;

    public DrawerCore(String ownerName, String drawerName, int layout, int privacy) {
        this.ownerName = ownerName;
        this.drawerName = drawerName;
        this.layout = layout;
        this.privacy = privacy;
    }

    public DrawerCore(){}

    public String encodeToJson(){
        JSONObject drawerJson = new JSONObject();
        drawerJson.put("ownerName", ownerName);
        drawerJson.put("drawerName", drawerName);
        drawerJson.put("layout", layout);
        return drawerJson.toJSONString();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }
}
