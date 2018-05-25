package org.minenite.plugin.core.storage.yaml;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.minenite.plugin.MineNite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet & AndrewAubury 2018
// https://www.piggypiglet.me
// https://www.andrewa.pw
// ------------------------------
@Singleton
public final class MFile {
    @Inject private org.minenite.plugin.core.utils.file.FileUtils fileUtils;

    private Map<String, Map<String, Object>> itemMaps;

    public MFile() {
        itemMaps = new HashMap<>();
    }

    public void make(String name, String externalPath, String internalPath) {
        File file = new File(externalPath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    if (fileUtils.exportResource(MineNite.class.getResourceAsStream(internalPath), externalPath)) {
                        insertIntoMap(name, file);

                        System.out.println(name + " successfully created & loaded.");
                    } else {
                        System.out.println(name + " creation failed.");
                    }
                } else {
                    System.out.println(name + " creation failed.");
                }
            } else {
                insertIntoMap(name, file);

                System.out.println(name + " successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isYaml(File file) {
        return file.getPath().endsWith(".yml");
    }

    private void insertIntoMap(String name, File file) {
        Map<String, Object> tempMap = new HashMap<>();
        itemMaps.put(name, tempMap);

        try {
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            if (isYaml(file)) {
                FileConfiguration fileConfiguration = new YamlConfiguration();
                fileConfiguration.load(file);

                itemMaps.get(name).put("file-configuration", fileConfiguration);
            } else {
                itemMaps.get(name).put("file-content", fileContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemMaps.get(name).put("file", file);
    }

    public FileConfiguration getFileConfiguration(String name) {
        Object item = itemMaps.get(name).get("file-configuration");

        if (item instanceof FileConfiguration) {
            return (FileConfiguration) item;
        }

        return new YamlConfiguration();
    }
}
