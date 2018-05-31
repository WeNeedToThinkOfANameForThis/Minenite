package org.minenite.plugin.commands;

import com.google.inject.Inject;
import es.pollitoyeye.vehicles.VehiclesMain;
import es.pollitoyeye.vehicles.enums.VehicleType;
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

            VehicleType type = (VehicleType) VehiclesMain.getPlugin().vehicleSubTypesMap.keySet().toArray()[5];
            ParachuteType subType = new ParachuteType(new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.COAL_BLOCK), 10.00, p.getName()+"-PARACHUTE", p.getName()+"-PARACHUTE", 5.00, 100.00, "");
            ArmorStand tmp = (VehicleType.PARACHUTE).getVehicleManager().spawn(p.getLocation(), (p).getUniqueId().toString(), (subType).getName());
            tmp.setPassenger(p);
        }

        return false;
    }
}
