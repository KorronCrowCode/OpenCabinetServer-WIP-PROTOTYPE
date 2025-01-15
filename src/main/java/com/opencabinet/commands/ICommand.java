package com.opencabinet.commands;

import com.opencabinet.models.packagemodels.ReqPackage;
import org.json.simple.JSONObject;

import java.util.Map;

public interface ICommand {
    public String execute(Map<String, Object> args);
    public String getUsername();
    public Map<String, Object> unpackReq(JSONObject req);
}
