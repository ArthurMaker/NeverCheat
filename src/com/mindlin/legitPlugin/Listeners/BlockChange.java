package com.mindlin.legitPlugin.Listeners;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mindlin.legitPlugin.Data.PlayerData;

public class BlockChange implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		PlayerData.main.getLogger().info("Boop");
		blobify(e.getPlayer());
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		PlayerData.main.getLogger().info("Boop");
		CraftPlayer cp=(CraftPlayer)e.getPlayer();
		BlockChangerPacket bcp = new BlockChangerPacket(cp.getLocation(), 1,0);
		cp.getHandle().playerConnection.sendPacket(bcp);
		//blobify(e.getPlayer());
	}
	@SuppressWarnings("deprecation")
	public void blobify(Player p){
		if(PlayerData.get(p).xray){
			p.sendBlockChange(p.getLocation().subtract(0,2,0), Material.DIRT, (byte) 50);
		}
	}
}
