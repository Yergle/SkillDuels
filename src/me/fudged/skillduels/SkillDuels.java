package me.fudged.skillduels;

import org.bukkit.plugin.java.JavaPlugin;

import me.fudged.skillduels.arena.Arena;
import me.fudged.skillduels.arena.ArenaManager;
import me.fudged.skillduels.cmd.Duel;
import me.fudged.skillduels.files.Files;
import me.fudged.skillduels.listener.PlayerJoin;
import me.fudged.skillduels.listener.RequestAccept;
import me.fudged.skillduels.listener.RequestDecline;
import me.fudged.skillduels.listener.RequestSent;
import me.fudged.skillduels.request.RequestManager;

public class SkillDuels extends JavaPlugin {
	
	private static SkillDuels instance;
	
	private ArenaManager arenaManager;
	private RequestManager requestManager;
	private Files arenas, players;

	@Override
	public void onEnable(){
		instance = this;
		
		arenas = new Files("arenas");
		players = new Files("players");
		
		arenaManager = new ArenaManager();
		arenaManager.setupArenas();
		requestManager = new RequestManager();
		
		new PlayerJoin();
		new Duel();
		new RequestSent();
		new RequestAccept();
		new RequestDecline();
		
	}
	
	@Override
	public void onDisable(){
		arenaManager.saveArenas(); // Save arenas to config
		
		for(Arena arena : arenaManager.getArenas()){ // Stop all arenas in progress
			arena.stop(); 
		}
	}
	
	public static SkillDuels getInst(){
		return instance;
	}
	
	public Files getArenaFile(){
		return arenas;
	}
	
	public Files getPlayerFile(){
		return players;
	}
	
	public ArenaManager getArenaManager(){
		return arenaManager;
	}
	
	public RequestManager getRequestManager(){
		return requestManager;
	}

}
