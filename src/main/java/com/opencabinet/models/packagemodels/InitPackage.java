package com.opencabinet.models.packagemodels;

import com.opencabinet.Consts.CustomEnums;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InitPackage extends ReqPackage{
    private String username;
    private String password;
    public InitPackage(int header, JSONObject jsonObj){
        super.header = header;
        super.body = jsonObj;
    }

    private void unpackJsonString(){
        this.username = (String)super.body.get("username");
        this.password = (String)super.body.get("password");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
