package me.fudged.skillduels.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.event.RequestSentEvent;
import me.fudged.skillduels.request.Request;

public class RequestSent implements Listener {
	
	public RequestSent(){
		Bukkit.getServer().getPluginManager().registerEvents(this, SkillDuels.getInst());
	}

	@EventHandler
	public void onRequestSent(RequestSentEvent event){
		Player sender = event.getSender();
		Player receiver = event.getReceiver();
		
		if(SkillDuels.getInst().getArenaManager().getArena(receiver.getUniqueId()) != null){ // Receiver is in another duel 
			sender.sendMessage(ChatColor.GRAY + receiver.getName() + ChatColor.YELLOW + " is already in a duel");
			return;
		}

		if(SkillDuels.getInst().getRequestManager().getRequest(sender.getUniqueId(), receiver.getUniqueId()) != null){ // Spam prevention
			sender.sendMessage(ChatColor.YELLOW + "You already have an ongoing duel request with " + ChatColor.GRAY + receiver.getName());
			sender.sendMessage(ChatColor.YELLOW + "Tell them to accept with " + ChatColor.GRAY + "/duel accept " + sender.getName());
			return;
		}

		Request request = SkillDuels.getInst().getRequestManager().getRequestbyOwner(sender.getUniqueId()); // Check if sender currently has 

		if(request != null){ // Currently has a duel request sent, cancelling so new one can be sent
			Bukkit.getServer().getPlayer(request.getUUID2()).sendMessage(ChatColor.GRAY + sender.getName() + ChatColor.YELLOW + " has cancelled the duel request");
			request.setDeclinedStatus(true);
		}

		sender.sendMessage(ChatColor.YELLOW + "Duel request sent to " + ChatColor.GRAY + receiver.getName());
		receiver.sendMessage(ChatColor.YELLOW + "Player " + ChatColor.GRAY + sender.getName() + ChatColor.YELLOW + " has challenged you to a duel!" + 
				ChatColor.GRAY +  " /duel <accept/decline> " + sender.getName());
		receiver.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GRAY + "30 seconds" + ChatColor.YELLOW + " to accept the duel");

		SkillDuels.getInst().getRequestManager().createRequest(sender.getUniqueId(), receiver.getUniqueId()); // Create the request
		
	}
	
}
