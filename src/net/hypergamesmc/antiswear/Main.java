package net.hypergamesmc.antiswear;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.hypergamesmc.antiswear.cmds.AntiswearCommand;


public class Main extends JavaPlugin implements Listener {
	
	public String prefix = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("prefix"));
	
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("antiswear").setExecutor((CommandExecutor)new AntiswearCommand());
		System.out.println("[AntiSwear] Booting");
		this.saveDefaultConfig();
	}
	public void onDisable() {
		System.out.println("[AntiSwear] Shutting down");
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		List <String> words = this.getConfig().getStringList("blacklisted-words");
		if (!player.hasPermission("antiswear.bypass")) {
			for (String cens : words) {
				if (event.getMessage().contains(cens))	{
					event.setCancelled(true);
					player.sendMessage(prefix + " " + this.getConfig().getString("no-swear-message"));
					
		}
		
	}
		
}
		else {
			event.setCancelled(false);
		}
	}
	public static Plugin getInst() {
        return getPlugin(Main.class);
	}
	

}


