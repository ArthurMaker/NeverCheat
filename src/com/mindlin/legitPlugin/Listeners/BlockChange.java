package com.mindlin.legitPlugin.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mindlin.legitPlugin.Data.PlayerData;

public class BlockChange implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		
	}
	@SuppressWarnings("deprecation")
	public void blobify(Player p){
		if(PlayerData.get(p).xray){
			p.sendBlockChange(p.getLocation().subtract(0,2,0), Material.DIRT, (byte) 0);
		}
	}
}
