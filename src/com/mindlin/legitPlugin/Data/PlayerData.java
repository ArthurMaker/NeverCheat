package com.mindlin.legitPlugin.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.mindlin.legitPlugin.Main;

public class PlayerData implements Serializable{
	private static final long serialVersionUID = 6225454744199345044L;
	//static stuff
	public static Main main;
	static HashMap<String, PlayerData> data = new HashMap<String, PlayerData>();
	public static void registerLogin(Player p){
		if(!isRegistered(p)){
			data.put(p.getName(), new PlayerData(p));
		}
	}
	public static void flush(){
		data.clear();
	}
	public static void save(){
		String path=main.getDataFolder() + File.separator + "players.bin";
		File f=new File(path);
		try {
			f.createNewFile();
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(PlayerData.data);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public static void load(){
		String path=main.getDataFolder() + File.separator + "players.bin";
		File f=new File(path);
		if(!f.exists()){main.getLogger().info("Couldn't load player data");return;}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			Object result = ois.readObject();
			ois.close();
			PlayerData.data=(HashMap<String, PlayerData>)result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean isRegistered(Player p){
		return isRegistered(p.getName());
	}
	public static boolean isRegistered(String s){
		return data.containsKey(s);
	}
	public static void updateAll(){
		Iterator<String> i=data.keySet().iterator();
		while(i.hasNext()){
			data.get(i.next()).update();
		}
	}
	//instance stuff
	public PermissionAttachment pa;
	public PlayerData(){}
	public OfflinePlayer op;
	public List<String> perms=new LinkedList<String>();
	public PlayerData(Player p){
		op=p.getPlayer();
	}
	public void givePermission(String p){
		if(p!="OP"){
			perms.add(p);
		}else{
			op.setOp(true);
		}
	}
	public void update(){
		ListIterator<String> li=perms.listIterator();
		Player p=(Player) (op.isOnline()?Bukkit.getPlayer(op.getName()):op);
		pa.remove();
		pa=p.addAttachment(main);
		while(li.hasNext()){
			pa.setPermission(li.next(), true);
		}
	}
	public void revokePermission(String p){
		if(p!="OP")perms.remove(p);
		op.setOp(false);
	}
}
