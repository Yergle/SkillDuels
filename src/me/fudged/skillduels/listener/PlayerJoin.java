package me.fudged.skillduels.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.fudged.skillduels.SkillDuels;

public class PlayerJoin implements Listener {

	public PlayerJoin() {
		Bukkit.getServer().getPluginManager().registerEvents(this, SkillDuels.getInst());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		UUID uu = event.getPlayer().getUniqueId();
		if(SkillDuels.getInst().getPlayerFile().get(uu.toString()) == null){
			SkillDuels.getInst().getPlayerFile().set(uu.toString(), "0;0;0");
		}
	}

}
