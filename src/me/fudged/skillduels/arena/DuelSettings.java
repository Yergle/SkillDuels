package me.fudged.skillduels.arena;

public enum DuelSettings {
	
	REGEN("Natural Regeneration"), POTIONS("Potions"), HUNGER("Hunger Loss"), DURABILITY("Durability"), ENDERPEARL("Enderpearls");
	
	private String string;
	
	DuelSettings(String string){
		this.string = string;
	}
	
	public String getString(){
		return string;
	}

}
