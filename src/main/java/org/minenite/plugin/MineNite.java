package org.minenite.plugin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.minenite.plugin.core.framework.BinderModule;
import org.minenite.plugin.events.TestEvents;
import org.minenite.plugin.managers.PlayerManager;
import org.minenite.plugin.objects.MineNitePlayer;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------

public class MineNite extends JavaPlugin {
    @Inject private TestEvents testEvents;

    @Inject private PlayerManager playerManager;

    @Getter private BukkitScheduler scheduler = getServer().getScheduler();
    @Getter public static MineNite mineNite;
    @Override


    public void onEnable() {
        mineNite = this;
        BinderModule module = new BinderModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        getServer().getPluginManager().registerEvents(testEvents, this);
    }
}
