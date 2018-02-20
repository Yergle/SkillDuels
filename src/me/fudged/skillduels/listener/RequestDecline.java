package me.fudged.skillduels.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.event.RequestDeclineEvent;
import me.fudged.skillduels.request.Request;

public class RequestDecline implements Listener {
	
	public RequestDecline(){
		Bukkit.getServer().getPluginManager().registerEvents(this, SkillDuels.getInst());
	}
	
	@EventHandler
	public void onRequestDecline(RequestDeclineEvent event){
		Player sender = event.getSender();
		Player receiver = event.getReceiver();
		Request request = event.getRequest();
		
		request.getTimer().cancel();
		SkillDuels.getInst().getRequestManager().removeRequest(request);
		
		receiver.sendMessage(ChatColor.GRAY + sender.getName() + ChatColor.YELLOW + " has declined the duel");
		sender.sendMessage(ChatColor.YELLOW + "You have declined " + ChatColor.GRAY + receiver.getName() + "'s " + ChatColor.YELLOW + "duel");
	}
	
}
