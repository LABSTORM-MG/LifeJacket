package me.labstorm.lifejacket.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;

public class Config {

    private final File file;
    private final YamlConfiguration config;

    private final String PLUGIN_NAME = "LifeJacket";

    public Config() {
        File dir = new File("./plugins/" + PLUGIN_NAME + "/");

        boolean dirWasCreated = false;
        if (!dir.exists()) {
            dirWasCreated = dir.mkdirs();
        }

        this.file = new File(dir, "config.yml");

        boolean fileWasCreated = false;
        if (!file.exists()) {
            try {
                fileWasCreated = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = loadConfiguration(file);

        if (dirWasCreated || fileWasCreated) {
            generateConfig();
            save();
        }
    }

    private void generateConfig() {
        config.options()
              .setHeader(asList("-".repeat(PLUGIN_NAME.length()), PLUGIN_NAME, "-".repeat(PLUGIN_NAME.length())));
        config.set("updraft", 0.1);
        config.setComments("updraft", List.of("Force of updraft."));
        config.set("sink-depth", 0.5);
        config.setComments("sink-depth",
                           asList("Distance between player eyes and water surface.",
                                  "Better keep this value somewhere between 0 and 2..."));
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}