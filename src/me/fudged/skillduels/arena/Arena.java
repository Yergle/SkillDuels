package me.fudged.skillduels.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.util.Util;

public class Arena {
	
	private List<UUID> players;
	private List<DuelSettings> settings;
	private Location spawn1, spawn2;
	private int id;
	private GameState state;
	
	private ItemStack[] items1, items2, armor1, armor2;
	private Location loc1, loc2;

	public Arena(int id, Location spawn1, Location spawn2) {
		this.id = id;
		this.players = new ArrayList<>();
		this.spawn1 = spawn1;
		this.spawn2 = spawn2;
		this.state = GameState.OPEN;
		this.settings = new ArrayList<>();
	}
	
	public Arena(int id){
		this.id = id;
		this.players = new ArrayList<>();
		this.spawn1 = Util.parseLocation(SkillDuels.getInst().getArenaFile().get("arenas." + id + ".spawn1"));
		this.spawn2 = Util.parseLocation(SkillDuels.getInst().getArenaFile().get("arenas." + id + ".spawn2"));
		this.state = GameState.OPEN;
		this.settings = new ArrayList<>();
	}
	
	public int getId(){
		return id;
	}
	
	public List<UUID> getPlayers(){
		return players;
	}
	
	public List<DuelSettings> getSettings(){
		return settings;
	}
	
	public Location getSpawn1(){
		return spawn1;
	}
	
	public Location getSpawn2(){
		return spawn2;
	}
	
	public GameState getState(){
		return state;
	}
	
	public void setGameState(GameState state){
		this.state = state;
	}
	
	public void setSpawn1(Location location){
		spawn1 = location;
	}
	
	public void setSpawn2(Location location){
		spawn2 = location;
	}

	public void stop() {
		
	}
	
	public void storeItems(){
		Player p1 = Bukkit.getServer().getPlayer(players.get(0));
		Player p2 = Bukkit.getServer().getPlayer(players.get(1));
		
		this.items1 = p1.getInventory().getContents();
		this.items2 = p2.getInventory().getContents();
		this.armor1 = p1.getInventory().getArmorContents();
		this.armor2 = p2.getInventory().getArmorContents();
		this.loc1 = p1.getLocation();
		this.loc2 = p1.getLocation();
	}
	
	public void restoreItems(){
		Player p1 = Bukkit.getServer().getPlayer(players.get(0));
		Player p2 = Bukkit.getServer().getPlayer(players.get(1));
		
		p1.getInventory().clear();
		p2.getInventory().clear();
		
		p1.teleport(loc1);
		p2.teleport(loc2);
		p1.getInventory().setContents(items1);
		p2.getInventory().setContents(items2);
		p1.getInventory().setArmorContents(armor1);
		p2.getInventory().setArmorContents(armor2);	
	}

}
