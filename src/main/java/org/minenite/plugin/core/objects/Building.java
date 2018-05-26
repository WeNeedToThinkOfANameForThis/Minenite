package org.minenite.plugin.core.objects;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.FaweQueue;
import com.boydti.fawe.object.RunnableVal;
import com.boydti.fawe.util.TaskManager;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;
import org.minenite.plugin.MineNite;

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
        FaweQueue queue = FaweAPI.createQueue(startLoc.getWorld().toString(), true);
TaskManager.IMP.runUnsafe(queue,new Runnable() {
    @Override
    public void run() {

        for(Vector v : building.keySet()){
            Location startLoc2 = startLoc.clone();
            startLoc2 = startLoc2.add(new Location(startLoc.getWorld(),v.getBlockX(),v.getBlockY(),v.getBlockZ()));
            if(startLoc2.getBlock().getType() == Material.AIR) {
                Location finalStartLoc = startLoc2;
                finalStartLoc.getBlock().setType(Material.BARRIER);
                ThreadLocalRandom r = ThreadLocalRandom.current();
                int low = 1;
                int high = 60;
                int result = r.nextInt(high-low) + low;
                MineNite mineNite = MineNite.getPlugin(MineNite.class);
                mineNite.getScheduler().runTaskLater(mineNite, () -> finalStartLoc.getBlock().setType(building.get(v)),result);
            }
        }

    }
});

    }
}
