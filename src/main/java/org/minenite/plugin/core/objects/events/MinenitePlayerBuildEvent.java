package org.minenite.plugin.core.objects.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.minenite.plugin.core.objects.Building;
import org.minenite.plugin.core.objects.MineNitePlayer;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class MinenitePlayerBuildEvent extends Event implements Cancellable {
    @Getter private static final HandlerList handlers = new HandlerList();
    @Setter @Getter boolean cancelled;

    @Getter MineNitePlayer player;
    @Getter Building building;
    @Getter @Setter Location location;

    public MinenitePlayerBuildEvent(MineNitePlayer MNPlayer, Building building , Location loc) {
        this.player = MNPlayer;
        this.building = building;
        this.location = loc;
        
    }
}
