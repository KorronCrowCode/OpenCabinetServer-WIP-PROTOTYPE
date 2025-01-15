package com.opencabinet.commands.Req;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.commands.ICommand;
import com.opencabinet.db.MainController;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.drawer.DrawerCoreItems;
import com.opencabinet.models.item.ItemCore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqValidDrawers implements ICommand {
    private MainController db;
    public CustomEnums.REQ_MODE action = CustomEnums.REQ_MODE.REQ_VALID_DRAWERS;
    private String username;
    private List<DrawerCore> drawerCores;

    public ReqValidDrawers() {}

    @Override
    public String execute(Map<String, Object> params) {
        init(params);
        drawerCores = db.dc.getValidDrawerCores(username);
        return packageReq();
    }

    private void init(Map<String, Object> params) {
        this.username = (String) params.get("username");
    }

    private String packageReq(){

        //array of drawer pointers
        JSONObject drawersObject = new JSONObject();
        JSONArray drawerCoreArray = new JSONArray();
        for(DrawerCore dc : drawerCores){
            drawerCoreArray.add(dc.encodeToJson());
        }
        drawersObject.put("drawerCores", drawerCoreArray);

        //header
        JSONObject headerObject = new JSONObject();
        headerObject.put("header", action.getValue());

        //combine
        JSONObject fullObject = new JSONObject();
        fullObject.put("header", headerObject);
        fullObject.put("body", drawersObject);

        return fullObject.toJSONString();
    }

    @Override
    public Map<String, Object> unpackReq(JSONObject req) {
        Map<String, Object> reqMap = new HashMap<>();
        JSONParser parser = new JSONParser();
        reqMap.put("username", req.get("username"));
        return reqMap;
    }
    @Override
    public String getUsername() {
        return username;
    }
}
