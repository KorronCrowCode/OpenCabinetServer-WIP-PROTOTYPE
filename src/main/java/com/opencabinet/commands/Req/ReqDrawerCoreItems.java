package com.opencabinet.commands.Req;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.db.MainController;
import com.opencabinet.models.drawer.DrawerCoreItems;
import com.opencabinet.commands.ICommand;
import com.opencabinet.models.drawer.DrawerPointerItems;
import com.opencabinet.models.item.ItemCore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqDrawerCoreItems implements ICommand {

    private MainController db;
    public CustomEnums.REQ_MODE action = CustomEnums.REQ_MODE.REQ_DRAWER_ITEMS;
    private String username;
    private List<ItemCore> itemCores;
    private String drawerName;

    public ReqDrawerCoreItems() {}

    @Override
    public String execute(Map<String, Object> params) {
        init(params);
        DrawerCoreItems drawer = db.dc.getValidDrawerCoreItems(drawerName, username);
        itemCores = drawer.getItemCores();
        return packageReq();
    }

    private void init(Map<String, Object> params) {
        this.username = (String) params.get("username");
        this.drawerName = (String) params.get("drawerName");
    }

    private String packageReq(){

        //array of drawer pointers
        JSONObject itemsObject = new JSONObject();
        JSONArray itemCoreArray = new JSONArray();
        for(ItemCore ic : itemCores){
            itemCoreArray.add(ic.encodeToJson());
        }
        itemsObject.put("drawerPointers", itemCoreArray);

        //header
        JSONObject headerObject = new JSONObject();
        headerObject.put("header", action.getValue());

        //combine
        JSONObject fullObject = new JSONObject();
        fullObject.put("header", headerObject);
        fullObject.put("body", itemsObject);

        return fullObject.toJSONString();
    }

    @Override
    public Map<String, Object> unpackReq(JSONObject req) {
        Map<String, Object> reqMap = new HashMap<>();
        JSONParser parser = new JSONParser();
        reqMap.put("username", req.get("username"));
        reqMap.put("drawerName", req.get("drawerName"));
        return reqMap;
    }
    @Override
    public String getUsername() {
        return username;
    }
}
