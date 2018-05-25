package org.minenite.plugin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.minenite.plugin.core.framework.Command;
import org.minenite.plugin.core.objects.enums.CommandsEnum;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class GMS extends Command {
    public GMS() {
        super(CommandsEnum.GMS);
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setGameMode(GameMode.SURVIVAL);
        }

        return false;
    }
}
