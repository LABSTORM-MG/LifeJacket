package me.labstorm.lifejacket.utils;

import me.labstorm.lifejacket.Main;
import org.bukkit.configuration.file.YamlConfiguration;

public class Utils {

    public static void reload() {
        Main.confObj = new Config();
        YamlConfiguration config = Main.getConfiguration();
        Main.UPDRAFT = config.getDouble("updraft");
        Main.SINK_DEPTH = config.getDouble("sink-depth");
    }

}
