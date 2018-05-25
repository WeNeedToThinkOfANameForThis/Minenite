package org.minenite.plugin.core.handlers;

import com.google.inject.Inject;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.minenite.plugin.MineNite;
import org.minenite.plugin.core.framework.Command;
import org.minenite.plugin.core.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class CommandHandler implements CommandExecutor {
    @Inject private MineNite mineNite;
    @Inject private StringUtils stringUtils;

    private List<Command> commands;

    public CommandHandler() {
        commands = new ArrayList<>();
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command bukkitCommand, String label, String[] badArgs) {
        for (Command command : commands) {
            String msg = Arrays.stream(badArgs).collect(Collectors.joining(" "));
            String name = command.getName().toString().toLowerCase();

            if (stringUtils.startsWith(msg, name)) {
                String[] args = msg.toLowerCase().replace(name, "").trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                command.run(sender, args[0].isEmpty() ? new String[]{} : args);
            }

            if (command.sendError()) {
                // error handler
            }
        }
        return true;
    }
}
