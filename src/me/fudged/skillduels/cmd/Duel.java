package me.fudged.skillduels.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.event.RequestAcceptEvent;
import me.fudged.skillduels.event.RequestDeclineEvent;
import me.fudged.skillduels.event.RequestSentEvent;
import me.fudged.skillduels.request.Request;

public class Duel implements CommandExecutor {

	public Duel() {
		SkillDuels.getInst().getCommand("duel").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			sender.sendMessage("Only players are allowed to use SkillDuels commands");
			return true;
		}
		Player player = (Player) sender;

		if(args.length == 0){ // Send help message
			player.sendMessage(ChatColor.YELLOW + "/duel help" + ChatColor.WHITE + " - " + ChatColor.GRAY + "See all duel commands");
			player.sendMessage(ChatColor.YELLOW + "/duel <name>" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Challenge someone to a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel accept" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Accept a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel decline" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Decline a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel stats" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Show your personal stats");
			return true;
		}
		// Help command
		if(args[0].equalsIgnoreCase("help")){
			player.sendMessage(ChatColor.YELLOW + "/duel help" + ChatColor.WHITE + " - " + ChatColor.GRAY + "See all duel commands");
			player.sendMessage(ChatColor.YELLOW + "/duel <name>" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Challenge someone to a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel accept" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Accept a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel decline" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Decline a duel");
			player.sendMessage(ChatColor.YELLOW + "/duel stats" + ChatColor.WHITE + " - " + ChatColor.GRAY + "Show your personal stats");
			return true;
		}
		// Stats command
		if(args[0].equalsIgnoreCase("stats")){
			String[] stats = SkillDuels.getInst().getPlayerFile().get(player.getUniqueId().toString()).toString().split(";");
			int won = Integer.parseInt(stats[1]);
			int lost = Integer.parseInt(stats[2]);
			int winPercentage = 100;

			if(lost != 0){ // Prevent division by 0
				winPercentage = won/lost;
			}

			player.sendMessage(ChatColor.YELLOW + "Duels Played: " + ChatColor.GRAY + stats[0]);
			player.sendMessage(ChatColor.YELLOW + "Duels Won: " + ChatColor.GRAY + won);
			player.sendMessage(ChatColor.YELLOW + "Duels Lost: " + ChatColor.GRAY + lost);
			player.sendMessage(ChatColor.YELLOW + "Win Percentage: " + ChatColor.GRAY + winPercentage + "%");
			return true;
		}
		// Accepting a duel
		if(args[0].equalsIgnoreCase("accept") && args.length == 2){
			Player target = Bukkit.getServer().getPlayer(args[1]);

			if(target == null){ // Target is not online/found
				player.sendMessage(ChatColor.YELLOW + "Player " + ChatColor.GRAY + args[1] + ChatColor.YELLOW + " cannot be found");
				return true;
			}

			Request request = SkillDuels.getInst().getRequestManager().getRequest(target.getUniqueId(), player.getUniqueId());

			if(request == null){ // No request with the two players
				player.sendMessage(ChatColor.YELLOW + "You do not have an invite from " + ChatColor.GRAY + target.getName());
				return true;
			}

			if(SkillDuels.getInst().getArenaManager().getArena(target.getUniqueId()) != null){ // Target is currently in a duel
				player.sendMessage(ChatColor.GRAY + target.getName() + ChatColor.YELLOW + " is currently in a duel");
				request.setDeclinedStatus(true);
				return true;
			}
			
			callEvent(new RequestAcceptEvent(request, player, target)); // Call RequestAcceptEvent

			
			return true;
		}

		if(args[0].equalsIgnoreCase("decline") && args.length == 2){
			Player target = Bukkit.getServer().getPlayer(args[1]);

			if(target == null){ // Target not online/found
				player.sendMessage(ChatColor.YELLOW + "Player " + ChatColor.GRAY + args[1] + ChatColor.YELLOW + " cannot be found");
				return true;
			}

			Request request = SkillDuels.getInst().getRequestManager().getRequest(target.getUniqueId(), player.getUniqueId());

			if(request == null){ // No request with the two players
				player.sendMessage(ChatColor.YELLOW + "You do not have an invite from " + ChatColor.GRAY + target.getName());
				return true;
			}

			callEvent(new RequestDeclineEvent(request, player, target)); // Call RequestDeclineEvent
			
			return true;
		}

		// Request invite
		
		Player target = Bukkit.getServer().getPlayer(args[0]);

		if(target == null){ // Target is not online/found
			player.sendMessage(ChatColor.YELLOW + "Player " + ChatColor.GRAY + args[0] + ChatColor.YELLOW + " cannot be found");
			return true;
		}

		callEvent(new RequestSentEvent(player, target)); // Call RequestSentEvent
		
		return true;
	}
	
	public void callEvent(Event event){
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

}
