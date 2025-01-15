package com.opencabinet.commands.Req;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.db.MainController;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.item.ItemPointer;
import com.opencabinet.commands.ICommand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class ReqDrawerCore implements ICommand {

    private MainController db = new MainController();
    public CustomEnums.REQ_MODE action = CustomEnums.REQ_MODE.REQ_DRAWER_CORE;
    private String drawerName;
    private DrawerCore drawer;
    private String username;

    public ReqDrawerCore() {}

    public String execute(Map<String, Object> params){
        init(params);
        drawer = db.dc.getDrawerCore(drawerName);
        return packageReq();
    }

    private void init(Map<String, Object> params){
        this.drawerName = (String) params.get("drawerName");
        this.username = (String) params.get("username");
    }


    private String packageReq(){
        //drawer object
        JSONObject drawerObject = new JSONObject();
        drawerObject.put("owner", drawer.getOwnerName());
        drawerObject.put("name", drawer.getDrawerName());
        drawerObject.put("layout", drawer.getLayout());
        drawerObject.put("privacy", drawer.getPrivacy());

        //header
        JSONObject headerObject = new JSONObject();
        headerObject.put("header", action.getValue());

        //combine
        JSONObject fullObject = new JSONObject();
        fullObject.put("header", headerObject);
        fullObject.put("body", drawerObject);

        return fullObject.toJSONString();
    }

    public Map<String, Object> unpackReq(JSONObject req) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        JSONParser parser = new JSONParser();
        reqMap.put("username", req.get("username"));
        reqMap.put("drawerName", req.get("drawerName"));
        return reqMap;
    }

    public String getUsername() {
        return username;
    }

}
