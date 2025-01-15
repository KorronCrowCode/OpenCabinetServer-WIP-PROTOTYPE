package com.opencabinet.commands.Req;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.commands.ICommand;
import com.opencabinet.db.MainController;
import com.opencabinet.models.item.ItemCore;
import com.opencabinet.models.item.ItemPackage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class ReqItemPackage implements ICommand {
    private MainController db = new MainController();
    private CustomEnums.REQ_MODE action = CustomEnums.REQ_MODE.REQ_ITEM_CORE;
    private String drawerName;
    private String itemName;
    private ItemPackage item;
    private String username;

    public ReqItemPackage() {}
    @Override
    public String execute(Map<String, Object> params) {
        init(params);

        item = db.ic.getItemPackage(drawerName, itemName);
        return packageReq();
    }

    private void init(Map<String, Object> params) {
        this.drawerName = (String) params.get("drawerName");
        this.itemName = (String) params.get("itemName");
        this.username = (String) params.get("username");
    }

    private String packageReq(){
        //item object
        JSONObject itemObject = new JSONObject();
        itemObject.put("name", item.getName());
        itemObject.put("layout", item.getLayout());

        //header
        JSONObject headerObject = new JSONObject();
        headerObject.put("header", action.getValue());

        //combine
        JSONObject fullObject = new JSONObject();
        fullObject.put("header", headerObject);
        fullObject.put("body", itemObject);

        return fullObject.toJSONString();
    }
    @Override
    public Map<String, Object> unpackReq(JSONObject req) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        JSONParser parser = new JSONParser();
        reqMap.put("username", req.get("username"));
        reqMap.put("drawerName", req.get("drawerName"));
        reqMap.put("itemName", req.get("itemName"));
        return reqMap;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
