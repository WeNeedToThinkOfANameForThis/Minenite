package org.minenite.plugin.commands;

import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.minenite.plugin.core.framework.Command;
import org.minenite.plugin.core.managers.PlayerManager;
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
public final class build extends Command {
    @Inject private PlayerManager pm;

    public build() {
        super(CommandsEnum.BUILD);
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            MineNitePlayer MNPlayer = pm.getPlayer(p);
            MNPlayer.sendMessage("&cBuilding Now");
            new FlatBuilding().build(p.getLocation());
        }

        return false;
    }
}
