package org.minenite.plugin.commands;

import com.google.inject.Inject;
import es.pollitoyeye.vehicles.VehiclesMain;
import es.pollitoyeye.vehicles.enums.VehicleType;
import es.pollitoyeye.vehicles.events.VehiclePlaceEvent;
import es.pollitoyeye.vehicles.interfaces.VehicleSubType;
import es.pollitoyeye.vehicles.vehicle.Parachute;
import es.pollitoyeye.vehicles.vehiclemanagers.ParachuteManager;
import es.pollitoyeye.vehicles.vehicletypes.ParachuteType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.minenite.plugin.core.framework.Command;
import org.minenite.plugin.core.objects.enums.CommandsEnum;
import org.minenite.plugin.core.storage.yaml.MFile;
import org.minenite.plugin.core.utils.player.XPUtils;

import java.util.HashMap;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class Test extends Command {
    @Inject private XPUtils xpUtils;
    @Inject private MFile mFile;

    public Test() {
        super(CommandsEnum.TEST);
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            List<VehicleSubType> list = VehiclesMain.getPlugin().vehicleSubTypesMap.get(VehicleType.PARACHUTE);

            //VehicleSubType sub = list.get(list.size()-1);
            ArmorStand tmp = (VehicleType.PARACHUTE).getVehicleManager().spawn(p.getLocation(), (p).getUniqueId().toString(), "MINENITE");
            tmp.setPassenger(p);

           // VehiclePlaceEvent e = new VehiclePlaceEvent(p,p.getLocation(),VehicleType.PARACHUTE,);
        }

        return false;
    }
}
