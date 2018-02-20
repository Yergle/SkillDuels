package me.fudged.skillduels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import me.fudged.skillduels.request.Request;

public class RequestDeclineEvent extends Event implements Listener {

	private static final HandlerList handlers = new HandlerList();
	
	private Request request;
	private Player sender, receiver;
	private boolean isCancelled;
	
	public RequestDeclineEvent(Request request, Player sender, Player receiver) {
		this.request = request;
		this.sender = sender;
		this.receiver = receiver;
		this.isCancelled = false;
	}
	
	public Request getRequest(){
		return request;
	}
	
	public Player getSender(){
		return sender;
	}
	
	public Player getReceiver(){
		return receiver;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean boo) {
		this.isCancelled = boo;
	}

	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
