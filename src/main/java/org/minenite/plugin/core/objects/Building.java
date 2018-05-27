package org.minenite.plugin.core.objects;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.FaweQueue;

import lombok.Data;
import org.bukkit.Location;

import java.util.ArrayList;

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
        FaweQueue queue = FaweAPI.createQueue(startLoc.getWorld().toString(), true);
        MineNite mn = MineNite.getPlugin(MineNite.class);
        File f = new File(mn.getDataFolder().getPath()+"/schematics/"+dir+"/"+sizeX+"x"+sizeZ+"/");
        if(f.exists()){
            mn.getSlf4jLogger().debug("File \""+f.getPath()+ "\" exist");
            String result = org.apache.commons.lang.StringUtils.join(f.list(), ", ");
            mn.getSlf4jLogger().debug(result);
        }else{
            mn.getSlf4jLogger().debug("File Non-existent");
        }
    }
}
