package org.minenite.plugin.commands;

import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
            Player player = (Player) sender;
            xpUtils.addLevel(player, 5);
            xpUtils.setPercentage(player, 0.5f);

            FileConfiguration config = mFile.getFileConfiguration("config");
            player.sendMessage(config.getString("test"));
        }

        return false;
    }
}
