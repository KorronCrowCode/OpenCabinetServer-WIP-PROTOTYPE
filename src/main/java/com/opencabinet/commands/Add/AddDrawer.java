package com.opencabinet.commands.Add;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.commands.ICommand;
import com.opencabinet.db.MainController;
import com.opencabinet.models.drawer.DrawerPointerItems;
import com.opencabinet.models.item.ItemPointer;
import com.opencabinet.models.packagemodels.ReqPackage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDrawer implements ICommand {

    private MainController db = new MainController();
    private String ownerName; //aka username
    private String drawerName;
    private int layout;
    private int privacy;

    private List<ItemPointer> items = new ArrayList<ItemPointer>();
    @Override
    public String execute(Map<String, Object> params) {
        init(params);
        DrawerPointerItems drawer = new DrawerPointerItems(ownerName, drawerName, layout, privacy, items);
        CustomEnums.REQ_MODE header = db.dc.AddDrawer(drawer);
        return packReq(header);
    }

    private String packReq(CustomEnums.REQ_MODE header) {
        ReqPackage req = new ReqPackage();
        req.setHeader(header.getValue());
        req.setBody(new JSONObject());
        return req.encodeToJson(req);
    }

    private void init(Map<String, Object> params) {
        ownerName = (String) params.get("ownerName");
        drawerName = (String) params.get("name");
        layout = (int)Integer.valueOf((String)params.get("layout"));
        privacy = (int)Integer.valueOf((String)params.get("privacy"));
    }

    @Override
    public String getUsername() {
        return this.ownerName;
    }

    @Override
    public Map<String, Object> unpackReq(JSONObject req) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        JSONParser parser = new JSONParser();
        reqMap.put("ownerName", req.get("ownerName"));
        reqMap.put("drawerName", req.get("drawerName"));
        reqMap.put("layout", req.get("layout"));
        reqMap.put("privacy", req.get("privacy"));
        return reqMap;
    }
}
