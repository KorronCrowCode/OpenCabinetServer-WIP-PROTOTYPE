package com.opencabinet.db;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.db.drawer.DrawerController;
import com.opencabinet.db.item.ItemController;
import com.opencabinet.db.user.UserController;
import com.opencabinet.models.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    public static DrawerController dc = new DrawerController();
    public static UserController uc = new UserController();
    public static ItemController ic = new ItemController();

}
