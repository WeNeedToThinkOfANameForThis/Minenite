package org.minenite.plugin.core.objects;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import org.bukkit.util.Vector;

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
    HashMap<Vector,Material> building = new HashMap<>();

    public void build(Location startLoc){
        for(Vector v : building.keySet()){
            Location startLoc2 = startLoc;
            startLoc2 = startLoc2.add(v);
            startLoc2.getBlock().setType(building.get(v));
        }
    }
}
