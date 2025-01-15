package com.opencabinet.db.repo;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempDrawerHolder {
    Map<String, JSONObject> drawerJsons = new HashMap<>();
    public TempDrawerHolder() {

    }

    public Map<String, JSONObject> getDrawerJsons() {
        return drawerJsons;
    }

    public void addDrawerJson(String title, JSONObject drawerJson) {
        drawerJsons.put(title, drawerJson);
    }
}
