package org.minenite.plugin.core.objects.buildings;

import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.objects.Building;

import java.util.HashMap;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public class FlatBuilding extends Building {
    public FlatBuilding(){
        setSizeX(10);
        setSizeY(1);
        setSizeZ(5);
        setSnapTo(5);
        recalculateBlocks();
    }

    public void recalculateBlocks(){
        setBuilding(new HashMap<Vector, Material>());
        for(int i = 0; i<getSizeX();i++){
            for(int ii = 0; ii<getSizeZ();ii++){
                getBuilding().put(new Vector(i,0,ii),Material.WOOD);
            }
        }
    }
}
