package org.minenite.plugin.commands;

import com.google.inject.Inject;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.minenite.plugin.core.framework.Command;
import org.minenite.plugin.core.managers.PlayerManager;
import org.minenite.plugin.core.objects.Building;
import org.minenite.plugin.core.objects.MineNitePlayer;
import org.minenite.plugin.core.objects.buildings.FlatBuilding;
import org.minenite.plugin.core.objects.enums.CommandsEnum;
import org.minenite.plugin.core.storage.yaml.MFile;
import org.minenite.plugin.core.utils.player.XPUtils;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class Build extends Command {
    @Inject private PlayerManager pm;

    public Build() {
        super(CommandsEnum.BUILD);
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            MineNitePlayer MNPlayer = pm.getPlayer(p);
            MNPlayer.sendMessage("&aBuilding Now");

            FlatBuilding build = new FlatBuilding();
            build.setSizeX(Integer.parseInt(args[0]));
            build.setSizeZ(Integer.parseInt(args[1]));
            build.recalculateBlocks();
            MNPlayer.build(build,p.getLocation().subtract(build.getSizeX()/2,1,build.getSizeZ()/2));

        }

        return false;
    }
}
