package org.minenite.plugin.core.utils.player;

import org.bukkit.entity.Player;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class XPUtils {
    public void setPercentage(Player player, float percentage) {
        player.setExp(percentage);
    }

    public void addLevel(Player player, int level) {
        player.setLevel(player.getLevel() + level);
    }
}
