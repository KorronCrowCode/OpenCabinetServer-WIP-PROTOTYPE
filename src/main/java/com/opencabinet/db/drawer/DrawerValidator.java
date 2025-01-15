package com.opencabinet.db.drawer;

import com.opencabinet.db.repo.RepoController;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.drawer.DrawerCoreItems;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Map;

public class DrawerValidator {
    private DrawerPacker dp = new DrawerPacker();
    private static RepoController rc = new RepoController();
    public DrawerValidator() {}
    protected boolean isValidDrawer(String username, JSONObject drawer){
        DrawerCore drawerCore = dp.unpackDrawerCore(drawer);
        if(username.equals(drawerCore.getOwnerName()) || drawerCore.getPrivacy()==0){
            return true;
        }else{
            return false;
        }
    }

    protected boolean drawerDetailsMatch(JSONObject f, String name){
        DrawerCore drawerPointer = dp.unpackDrawerCore(f);
        if(name.equals(drawerPointer.getDrawerName())) {
            return true;
        }else{return false;}
    }

    protected boolean drawerIsUnique(String name){

        Map<String, JSONObject> drawers = rc.tdh.getDrawerJsons();

        if(drawers.containsKey(name)) {
            return false;
        }else{
            return true;
        }

    }
}
