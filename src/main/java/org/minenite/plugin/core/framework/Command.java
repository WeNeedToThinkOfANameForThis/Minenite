package org.minenite.plugin.core.framework;

import org.bukkit.command.CommandSender;
import org.minenite.plugin.core.objects.enums.CommandsEnum;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public abstract class Command {
    private final CommandsEnum name;
    private boolean execute;

    protected Command() {
        this(null);
    }

    protected Command(CommandsEnum name) {
        this.name = name;
    }

    protected abstract boolean execute(CommandSender sender, String[] args);

    public void run(CommandSender sender, String[] args) {
        execute = execute(sender, args);
    }

    public CommandsEnum getName() {
        return name;
    }

    public boolean sendError() {
        return execute;
    }
}
