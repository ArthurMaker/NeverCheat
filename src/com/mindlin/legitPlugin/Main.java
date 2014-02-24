package com.mindlin.legitPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mindlin.legitPlugin.Data.PlayerData;
import com.mindlin.legitPlugin.Listeners.PlayerLogin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		PlayerData.main=this;
		getLogger().info("Hi");
		PlayerData.load();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerLogin(), this);
	}
	@Override
	public void onDisable(){
		PlayerData.save();
	}
}
