package org.minenite.plugin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.minenite.plugin.commands.Build;
import org.minenite.plugin.commands.GMC;
import org.minenite.plugin.commands.GMS;
import org.minenite.plugin.commands.Test;
import org.minenite.plugin.core.framework.BinderModule;
import org.minenite.plugin.core.handlers.CommandHandler;
import org.minenite.plugin.core.objects.enums.Registerables;
import org.minenite.plugin.core.storage.yaml.MFile;
import org.minenite.plugin.events.TestEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.minenite.plugin.core.objects.enums.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------

public final class MineNite extends JavaPlugin {
    @Inject private CommandHandler commandHandler;
    @Inject private MFile mFile;

    @Inject private TestEvents testEvents;

    @Inject private GMC gmc;
    @Inject private GMS gms;
    @Inject private Test test;
    @Inject private Build build;

    @Getter private final Logger slf4jLogger = LoggerFactory.getLogger(MineNite.class);
    @Override
    public void onEnable() {

        BinderModule module = new BinderModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        Stream.of(
                FILES, COMMANDS, LISTENERS
        ).forEach(this::register);
        getLogger().info("Minenite Started");
    }

    private void register(Registerables register) {
        switch (register) {
            case FILES:
                mFile.make("config", getDataFolder().getPath() + "/config.yml", "/config.yml");
                break;

            case COMMANDS:
                getCommand("minenite").setExecutor(commandHandler);

                Stream.of(
                        gmc, gms, test, build
                ).forEach(commandHandler.getCommands()::add);
                break;

            case LISTENERS:
                getServer().getPluginManager().registerEvents(testEvents, this);
                break;
        }
    }

    @Getter private BukkitScheduler scheduler = getServer().getScheduler();
}
