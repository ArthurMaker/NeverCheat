package com.mindlin.legitPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.mindlin.legitPlugin.Data.PlayerData;

public class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		PlayerData.main=this;
		getLogger().info("Hi");
		PlayerData.load();
	}
	@Override
	public void onDisable(){
		PlayerData.save();
	}
}
