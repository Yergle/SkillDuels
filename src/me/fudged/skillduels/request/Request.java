package me.fudged.skillduels.request;

import java.util.UUID;

import org.bukkit.scheduler.BukkitTask;

import me.fudged.skillduels.SkillDuels;

public class Request {
	
	private UUID uu1, uu2;
	private boolean accepted, decline;
	private BukkitTask timer;

	public Request(UUID uu1, UUID uu2) {
		this.uu1 = uu1;
		this.uu2 = uu2;
		this.accepted = false;
		this.decline = false;
		
		this.timer = new RequestTimer(this).runTaskTimer(SkillDuels.getInst(), 0L, 20L);
	}
	
	public void setAcceptedStatus(boolean boo){
		this.accepted = boo;
	}
	
	public void setDeclinedStatus(boolean boo){
		this.decline = boo;
	}
	
	public UUID getUUID1(){
		return uu1;
	}
	
	public UUID getUUID2(){
		return uu2;
	}
	
	public BukkitTask getTimer(){
		return timer;
	}
	
	public boolean getAcceptStatus(){
		return accepted;
	}
	
	public boolean getDeclinedStatus(){
		return decline;
	}

}
