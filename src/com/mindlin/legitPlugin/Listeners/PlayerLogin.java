package com.mindlin.legitPlugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.mindlin.legitPlugin.Data.PlayerData;

public class PlayerLogin implements Listener{
	public PlayerLogin(){
		
	}
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e){
		PlayerData.registerLogin(e.getPlayer());
	}
}
