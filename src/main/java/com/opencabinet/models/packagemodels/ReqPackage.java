package com.opencabinet.models.packagemodels;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReqPackage {

    protected int header;
    protected JSONObject body;

    public ReqPackage(String jsonString) {
        initJsonSetup(jsonString);
    }

    public ReqPackage() {}

    protected void initJsonSetup(String jsonString){
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(jsonString);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(obj);

            JSONObject jsonObj = (JSONObject) jsonArray.getFirst();

            String stringHeader = (String) jsonObj.get("header");
            this.header = (int) Integer.valueOf((String) jsonObj.get("header"));
            this.body = (JSONObject) jsonObj.get("body");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeToJson(ReqPackage reqPackage) {
        JSONObject fullPackage = new JSONObject();
        fullPackage.put("header", reqPackage.header);
        fullPackage.put("body", reqPackage.body);
        return fullPackage.toJSONString();
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
