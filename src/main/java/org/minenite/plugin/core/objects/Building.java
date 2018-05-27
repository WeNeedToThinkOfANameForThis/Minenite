package org.minenite.plugin.core.objects;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.FaweQueue;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import lombok.Data;
import org.bukkit.Location;

import java.util.*;

import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.utils.string.StringUtils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------

@Data
public class Building {
    int snapTo;
    int sizeX;
    int sizeY;
    int sizeZ;
    String dir;

    ArrayList files = new ArrayList<>();

    public void build(Location startLoc){
        //FaweQueue queue = FaweAPI.createQueue(startLoc.getWorld().toString(), true);
        MineNite mn = MineNite.getPlugin(MineNite.class);
        File f = new File(mn.getDataFolder().getPath()+"/schematics/"+dir+"/"+sizeX+"x"+sizeZ+"/");
        if(f.exists()){
            mn.getServer().broadcastMessage("File \""+f.getPath()+ "\" exist");
            List<String> stringList = new ArrayList<String>(Arrays.asList(f.list()));
            Collections.sort(stringList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
            });
            String result = org.apache.commons.lang.StringUtils.join(stringList, ", ");
           mn.getServer().broadcastMessage(result);

            for(String schem : stringList){
                mn.getScheduler().runTaskLater(mn, new Runnable() {
                    @Override
                    public void run() {
                        mn.getServer().broadcastMessage("Now placing: "+f.getPath()+"/"+schem);
                    }
                },stringList.indexOf(schem)*5);
            }

        }else{
            mn.getServer().broadcastMessage("File Non-existent");
        }

       // EditSession editSession = ClipboardFormat.SCHEMATIC.load(file).paste(world, position, allowUndo, !noAir, (Transform) null);
    }
}
