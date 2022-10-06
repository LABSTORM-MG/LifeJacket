package me.labstorm.lifejacket.commands;

import me.labstorm.lifejacket.Main;
import me.labstorm.lifejacket.utils.Config;
import me.labstorm.lifejacket.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LifeJacketReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            Utils.reload();
            commandSender.sendMessage(ChatColor.GREEN + "Config was reloaded!");
        } catch (Exception ex) {
            commandSender.sendMessage(ChatColor.RED + "There was an error while reloading the config!");
            if (Main.confObj.getFile().delete()) {
                Main.confObj = new Config();
                commandSender.sendMessage(ChatColor.RED + "Config has been reset to defaults.");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Couldn't find config file, please delete the config folder and restart the server.");
            }
        }
        return true;
    }
}
