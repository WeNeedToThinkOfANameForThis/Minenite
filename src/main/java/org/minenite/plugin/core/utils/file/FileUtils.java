package org.minenite.plugin.core.utils.file;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
public final class FileUtils {
    public boolean exportResource(InputStream source, String destination) {
        boolean success = true;

        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}
