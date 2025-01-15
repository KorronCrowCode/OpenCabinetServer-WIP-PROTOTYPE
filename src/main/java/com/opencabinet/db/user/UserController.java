package com.opencabinet.db.user;

import com.opencabinet.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserController {

    public UserController(){}
    private File[] getAllUserFiles(){
        File dir = new File("db/users");
        return dir.listFiles();
    }

    private boolean doUserDetailsMatch(File userF, String username, String password){
        User user = unpackUserJson(userF);
        if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            return true;
        }else{return false;}
    }

    public User getUser(String username){
        File[] dirListing = getAllUserFiles();
        User user = null;
        if(dirListing != null){
            for(File f : dirListing){
                if(doUserDetailsMatch(f,username,username)){
                    user= unpackUserJson(f);
                }
            }
        }
        return user;
    }

    private User unpackUserJson(File f){
        User unpackedUser = null;

        try {
            JSONArray jsonArray = (JSONArray) JSONValue.parse(new FileReader(f));
            for (Object o : jsonArray) {
                JSONObject user = (JSONObject) o;
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                unpackedUser = new User(username, password);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return unpackedUser;
    }

    public boolean validateLogIn(String username, String password){
        boolean valid = false;
        File[] dirListing = getAllUserFiles();
        if(dirListing != null){
            for(File f : dirListing){
                valid = doUserDetailsMatch(f,username,password);
            }
        }
        return valid;
    }
}
