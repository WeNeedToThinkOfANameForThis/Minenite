package org.minenite.plugin.core.utils.string;

import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class StringUtils {
    public boolean startsWith(String string, String start) {
        if (start.contains("/")) {
            String[] contain = start.split("/");
            return Arrays.stream(contain).anyMatch(string.toLowerCase()::startsWith);
        }

        return string.toLowerCase().startsWith(start.toLowerCase());
    }
}
