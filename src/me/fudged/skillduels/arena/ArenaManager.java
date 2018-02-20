package me.fudged.skillduels.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.util.Util;

public class ArenaManager {

	private List<Arena> arenas;

	public ArenaManager(){
		arenas = new ArrayList<Arena>();
	}

	public void setupArenas(){ // Load arenas from storage
		if(!arenas.isEmpty()){
			arenas.clear();
		}

		if(SkillDuels.getInst().getArenaFile().getSection("arenas") == null){
			SkillDuels.getInst().getArenaFile().creatSection("arenas");
		}

		for(String id : SkillDuels.getInst().getArenaFile().<ConfigurationSection>get("arenas").getKeys(false)){
			try {
				arenas.add(new Arena(Integer.parseInt(id)));
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public void saveArenas(){ // Save arenas to config file
		for(Arena arena : arenas){
			if(SkillDuels.getInst().getArenaFile().get("arenas." + arena.getId()) == null){ // Need to save the arena
				SkillDuels.getInst().getArenaFile().creatSection("arenas." + arena.getId());
				SkillDuels.getInst().getArenaFile().set("arenas." + arena.getId() + ".spawn1", Util.locationToString(arena.getSpawn1()));
				SkillDuels.getInst().getArenaFile().set("arenas." + arena.getId() + ".spawn2", Util.locationToString(arena.getSpawn2()));
			}
		}
	}

	public void createArena(Player player, int id){ // Create a new arena
		arenas.add(new Arena(id, player.getLocation(), player.getLocation()));
	}

	public Arena getArena(UUID uu){ // Get arena that a player is in
		for(Arena arena : arenas){
			if(arena.getPlayers().contains(uu)){
				return arena;
			}
		}
		return null;
	}

	public List<Arena> getArenas(){
		return arenas;
	}

}
