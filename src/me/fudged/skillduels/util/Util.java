package me.fudged.skillduels.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Util {

	public static String locationToString(Location location){
		String s = location.getWorld().getName().toString() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch();
		return s;
	}

	public static Location parseLocation(String string) {
		String [] locString = string.split(";");
		
		return new Location(Bukkit.getWorld(locString[0]), Double.parseDouble(locString[1]), 
				Double.parseDouble(locString[2]), Double.parseDouble(locString[3]), Float.parseFloat(locString[4]), Float.parseFloat(locString[5]));
	}

}
