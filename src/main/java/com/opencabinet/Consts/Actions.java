package com.opencabinet.Consts;

import com.opencabinet.commands.ICommand;
import com.opencabinet.commands.Req.ReqDrawerCore;
import com.opencabinet.commands.Req.ReqDrawerCoreItems;
import com.opencabinet.commands.Req.ReqItemCore;

import java.util.HashMap;
import java.util.Map;

public class Actions {
    public static Map<CustomEnums.REQ_MODE, ICommand> commands = new HashMap<>(){};
    public Actions() {
        putCommands();
    }
    private void putCommands(){
        commands.put(CustomEnums.REQ_MODE.REQ_DRAWER_ITEMS, new ReqDrawerCoreItems());
        commands.put(CustomEnums.REQ_MODE.REQ_DRAWER_CORE, new ReqDrawerCore());
        commands.put(CustomEnums.REQ_MODE.REQ_DRAWER_ITEMS, new ReqItemCore());
    }
}
