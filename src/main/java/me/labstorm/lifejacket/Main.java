package me.labstorm.lifejacket;

import me.labstorm.lifejacket.commands.LifeJacketReloadCommand;
import me.labstorm.lifejacket.listeners.PlayerMoveListener;
import me.labstorm.lifejacket.utils.Config;
import me.labstorm.lifejacket.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static double UPDRAFT;
    public static double SINK_DEPTH;

    public static Config confObj;

    public static YamlConfiguration getConfiguration() {
        return confObj.getConfig();
    }

    @Override
    public void onDisable() {
        confObj.save();
    }

    @Override
    public void onEnable() {
        Utils.reload();
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        getCommand("lifeJacketReload").setExecutor(new LifeJacketReloadCommand());
    }

}
