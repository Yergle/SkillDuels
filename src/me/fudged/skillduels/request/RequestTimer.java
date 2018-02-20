package me.fudged.skillduels.request;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.fudged.skillduels.SkillDuels;

public class RequestTimer extends BukkitRunnable {

	private int time = 30;
	private Request request;
	private Player sender, receiver;
	
	public RequestTimer(Request request) {
		this.request = request;
		this.sender = Bukkit.getServer().getPlayer(request.getUUID1());
		this.receiver = Bukkit.getServer().getPlayer(request.getUUID2());
	}

	@Override
	public void run() {
		if(time == 0){ // Request time has run out; cancel request
			sender.sendMessage(ChatColor.YELLOW + "Your duel request with " + ChatColor.GRAY + receiver.getName() + ChatColor.YELLOW + " has expired");
			sender.sendMessage(ChatColor.GRAY + receiver.getName() + "'s" + ChatColor.YELLOW + " duel request has expired");
			receiver.sendMessage(ChatColor.YELLOW + "The duel request with " + ChatColor.GRAY + sender.getName() + ChatColor.YELLOW + " has expired");
			
			SkillDuels.getInst().getRequestManager().removeRequest(request);
			this.cancel();
			return;
		}
		
		time--; // Decrease time by a second (1 minute total)
	}

}
