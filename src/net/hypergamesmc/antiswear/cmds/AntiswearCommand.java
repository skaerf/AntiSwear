package net.hypergamesmc.antiswear.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hypergamesmc.antiswear.Main;


public class AntiswearCommand implements CommandExecutor {
	
	public String info = ChatColor.translateAlternateColorCodes('&', "&6------- &cAntiSwear by skaerf &6-------\n"
			+ "&aYou are running version &61.0&a of AntiSwear on&6 " + Bukkit.getVersion() + "\n"
			+ "&aMake sure you check for updates, as we regularly add new features!\n"
			+ "&6Command Usage:\n"
			+ "&6/antiswear reload&f - Reloads the config and blacklist\n"
			+ "&6/antiswear info&f - Shows this page\n"
			+ "&6/antiswear blacklist&f - Shows every word on the blacklist");
	
	String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInst().getConfig().getString("prefix"));

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			player.sendMessage(info);
		}
		else if (args[0].equalsIgnoreCase("reload") && player.hasPermission("antiswear.admin")) {
			Main.getInst().reloadConfig();
			Main.getInst().saveConfig();
			player.sendMessage(prefix + ChatColor.GREEN + " Config reloaded!");
		}
		else if (args[0].equalsIgnoreCase("info")) {
			player.sendMessage(info);
		}
		else if (args[0].equalsIgnoreCase("reload") && !(player.hasPermission("antiswear.admin"))) {
			player.sendMessage(prefix + ChatColor.RED + " Sorry, you don't have permission to do this command!\n"
					+ ChatColor.RED + "The permission node is antiswear.admin");
		}
		else if (args[0].equalsIgnoreCase("blacklist") && player.hasPermission("antiswear.admin")) {
			player.sendMessage(prefix + " BLACKLISTED WORDS: " + Main.getInst().getConfig().getStringList("blacklisted-words"));	
		}
		else if (args[0].equalsIgnoreCase("blacklist") && !(player.hasPermission("antiswear.admin"))) {
			player.sendMessage(prefix + ChatColor.RED + " Sorry, you don't have permission to do this command!\n"
					+ ChatColor.RED + "The permission node is antiswear.admin");
		}
		return true;
	}

}

