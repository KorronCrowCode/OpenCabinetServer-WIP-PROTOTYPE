package com.opencabinet.db.drawer;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.db.repo.RepoController;
import com.opencabinet.db.repo.TempDrawerHolder;
import com.opencabinet.db.repo.TempNoteHolder;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.drawer.DrawerCoreItems;
import com.opencabinet.models.drawer.DrawerPointerItems;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrawerController {
    private DrawerPacker dp = new DrawerPacker();
    private DrawerValidator dv = new DrawerValidator();
    private static RepoController rc = new RepoController();
    public DrawerController() {}
    protected static Map<String, JSONObject> getAllDrawers(){
        return rc.tdh.getDrawerJsons();
    }

    public List<DrawerCore> getValidDrawerCores(String username){
        Map<String, JSONObject> drawerJsons = getAllDrawers();
        List<DrawerCore> drawerCores = new ArrayList<>();

        for(JSONObject drawerJson : drawerJsons.values()){
            if(dv.isValidDrawer(username, drawerJson)){
                drawerCores.add(dp.unpackDrawerCore(drawerJson));
            }
        }

        return drawerCores;
    }

    public DrawerCoreItems getValidDrawerCoreItems(String drawerName, String username){
        Map<String, JSONObject> drawerJsons = getAllDrawers();
        JSONObject drawerJson = drawerJsons.get(drawerName);
        DrawerCoreItems drawer = null;

        if(drawerJsons.containsKey(drawerName) && dv.isValidDrawer(username, drawerJson)){
            drawer = dp.unpackDrawerCoreItems(drawerJson);
        }

        return drawer;
    }

    public DrawerCore getDrawerCore(String drawerName){
        Map<String, JSONObject> drawerJsons = getAllDrawers();
        DrawerCore drawer = null;

        return drawer = dp.unpackDrawerCore(drawerJsons.get(drawerName));
    }


    public DrawerCoreItems getDrawerCoreItems(String drawerName){
        Map<String, JSONObject> drawerJsons = getAllDrawers();
        DrawerCoreItems drawer = null;

        return drawer = dp.unpackDrawerCoreItems(drawerJsons.get(drawerName));
    }

    public DrawerPointerItems getDrawerPointerItems(String drawerName){
        Map<String, JSONObject> drawerJsons = getAllDrawers();
        DrawerPointerItems drawer = null;

        return drawer = dp.unpackDrawerPointerItems(drawerJsons.get(drawerName));

    }

    public CustomEnums.REQ_MODE AddDrawer(DrawerPointerItems drawer){
        if(dv.drawerIsUnique(drawer.getDrawerName())) {
            File dir = new File("data/drawers/" + drawer.getDrawerName());
            String drawerJson = drawer.encodeToJson();

            File newJ = new File(dir, drawerJson);

            return CustomEnums.REQ_MODE.ADD_DRAWER_SUCCESS;
        }else{
            return CustomEnums.REQ_MODE.ADD_DRAWER_FAIL;
        }
    }

    private String encodeDrawerJsonString(DrawerPointerItems drawer){
        JSONObject drawerJson = new JSONObject();
        drawerJson.put("owner", drawer.getOwnerName());
        drawerJson.put("name", drawer.getDrawerName());
        drawerJson.put("layout", drawer.getLayout());
        drawerJson.put("items", drawer.getItemPointersJson()); //function that auto puts it into a json
        return drawerJson.toJSONString();
    }

}
