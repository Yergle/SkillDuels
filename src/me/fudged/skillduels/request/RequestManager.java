package me.fudged.skillduels.request;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RequestManager {

	private List<Request> requests;
	
	public RequestManager() {
		requests = new ArrayList<>();
	}
	
	public Request getRequest(UUID uu1, UUID uu2){ // Find request of two players
		for(Request request : requests){
			if(request.getUUID1() == uu1 && uu2 == request.getUUID2()){
				return request;
			}
		}
		return null;
	}
	
	public void createRequest(UUID uu1, UUID uu2){ // Create  request between two players
		requests.add(new Request(uu1, uu2));
	}
	
	public void removeRequests(UUID uu){ // Delete all requests for a certain player 
		for(Request match : requests){
			if(match.getUUID1() == uu || match.getUUID2() == uu){
				requests.remove(match);
			}
		}
	}
	
	public void removeRequest(UUID uu1, UUID uu2){ // Remove a certain request
		for(Request request : requests){
			if(request.getUUID1() == uu1 && uu2 == request.getUUID2()){
				requests.remove(request);
			}
		}
	}
	
	public void removeRequest(Request request){ // Remove a certain request
		if(requests.contains(request)){
			requests.remove(request);
		}
	}
	
	public Request getRequestbyOwner(UUID uu){
		for(Request request : requests){
			if(request.getUUID1() == uu){
				return request;
			}
		}
		return null;
	}

}
