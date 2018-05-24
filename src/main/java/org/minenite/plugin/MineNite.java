package org.minenite.plugin;

import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.minenite.plugin.core.framework.BinderModule;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class MineNite extends JavaPlugin {
    @Override
    public void onEnable() {
        BinderModule module = new BinderModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        //code here
    }
}
