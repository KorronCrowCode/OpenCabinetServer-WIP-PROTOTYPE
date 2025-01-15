package com.opencabinet.db.item;

import com.opencabinet.Consts.CustomEnums;
import com.opencabinet.commands.Req.ReqItemPackage;
import com.opencabinet.db.drawer.DrawerController;
import com.opencabinet.db.repo.RepoController;
import com.opencabinet.models.drawer.DrawerCore;
import com.opencabinet.models.drawer.DrawerCoreItems;
import com.opencabinet.models.drawer.DrawerPointerItems;
import com.opencabinet.models.item.ItemCore;
import com.opencabinet.models.item.ItemPackage;
import com.opencabinet.models.item.ItemPointer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ItemController {
    private DrawerController dc = new DrawerController();

    private static RepoController rc = new RepoController();

    public ItemController() {}

    protected static Map<String, String> getAllNotes(){
        return rc.tnh.getNotes();
    }

    public ItemCore getItemCore(String drawerName, String itemName){
        ItemCore item = null;
        DrawerCoreItems drawerCoreItems = dc.getDrawerCoreItems(drawerName);
        List<ItemCore> itemCores = drawerCoreItems.getItemCores();
        for(ItemCore itemCore : itemCores){
            if(itemCore.getName().equals(itemName)){
                item = itemCore;
                break;
            }
        }
        return item;
    }

    public ItemPackage getItemPackage(String drawerName, String itemName){

        ItemPackage item = null;
        ItemPointer itemP = null;
        DrawerPointerItems drawerPointerItems = dc.getDrawerPointerItems(drawerName);
        List<ItemPointer> itemPointers = drawerPointerItems.getItemPointers();

        for(ItemPointer itemPointer : itemPointers){
            if(itemPointer.getName().equals(itemName)){
                itemP=itemPointer;
                break;
            }
        }

        Map<String, String> notes = getAllNotes();
        String data = notes.get(itemName);

        item = new ItemPackage(itemP.getName(), itemP.getLayout(), data);

        return item;
    }

    public CustomEnums.REQ_MODE AddItems(String drawerName, ItemPackage itemPackage){
       DrawerPointerItems drawerPointerItems = dc.getDrawerPointerItems(drawerName);

       ItemPointer itemPointer = new ItemPointer(itemPackage.getName(), itemPackage.getLayout(), itemPackage.getName());

       if(itemPointer.getLayout()==CustomEnums.LAYOUT.NOTE.getValue()){
           String data = (String) itemPackage.getData();
           rc.tnh.addNote(itemPointer.getName(), data);
           drawerPointerItems.addItemPointer(itemPointer);
           return CustomEnums.REQ_MODE.ADD_DRAWER_SUCCESS;
       }else {
           return CustomEnums.REQ_MODE.ADD_DRAWER_FAIL;
       }
   }



}
