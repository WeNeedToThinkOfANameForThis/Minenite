package org.minenite.plugin.core.objects;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.FaweQueue;

import com.boydti.fawe.object.extent.FastWorldEditExtent;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.WorldEditAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.entity.BaseEntity;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.regions.Region;
import lombok.Data;
import org.bukkit.Location;

import java.util.*;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.utils.string.StringUtils;

import javax.xml.crypto.dsig.Transform;
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
    protected int snapTo;
    protected int sizeX;
    protected int sizeY;
    protected int sizeZ;
    protected String dir;

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

            Location toPlace = startLoc;
            toPlace = toPlace.add(getSizeX()/2,getSizeY()/2,getSizeZ()/2);

            for(String schem : stringList){
                Location finalToPlace = toPlace;
                BukkitTask bukkitTask = mn.getScheduler().runTaskLater(mn, new Runnable() {
                    @Override
                    public void run() {

                    //mn.getServer().broadcastMessage("Now placing: " + f.getPath() + "/" + schem);
                        try {
                            EditSession editSession = ClipboardFormat.SCHEMATIC.load(new File(f.getPath() + "/" + schem)).paste(BukkitUtil.getLocalWorld(finalToPlace.getWorld()),BukkitUtil.toVector(finalToPlace));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, stringList.indexOf(schem) * 5);
            }

        }else{
            mn.getServer().broadcastMessage("File Non-existent");
        }

       // EditSession editSession = ClipboardFormat.SCHEMATIC.load(file).paste(world, position, allowUndo, !noAir, (Transform) null);
    }
}
