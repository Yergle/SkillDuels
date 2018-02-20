package me.fudged.skillduels.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.event.RequestAcceptEvent;
import me.fudged.skillduels.request.Request;

public class RequestAccept implements Listener {
	
	public RequestAccept(){
		Bukkit.getServer().getPluginManager().registerEvents(this, SkillDuels.getInst());
	}

	@EventHandler
	public void onRequestAccept(RequestAcceptEvent event){
		Player sender = event.getSender();
		Player receiver = event.getReceiver();
		Request request = event.getRequest();
		
		request.getTimer().cancel();
		SkillDuels.getInst().getRequestManager().removeRequest(request);
		
		receiver.sendMessage(ChatColor.GRAY + receiver.getName() + ChatColor.YELLOW + " has accepted the duel");
		sender.sendMessage(ChatColor.YELLOW + "You have accepted " + ChatColor.GRAY + sender.getName() + "'s " + ChatColor.YELLOW + "duel");
	}

}
