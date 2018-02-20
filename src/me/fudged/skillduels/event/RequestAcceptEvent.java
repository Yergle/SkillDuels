package me.fudged.skillduels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.fudged.skillduels.request.Request;

public class RequestAcceptEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	private Request request;
	private Player sender, receiver;
	private boolean isCancelled;
	
	public RequestAcceptEvent(Request request, Player sender, Player receiver) {
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
