package me.fudged.skillduels.files;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.fudged.skillduels.SkillDuels;

public class Files {

	public Files(String name){

		if(!SkillDuels.getInst().getDataFolder().exists()){
			SkillDuels.getInst().getDataFolder().mkdir();
		}

		file = new File(SkillDuels.getInst().getDataFolder(), name + ".yml");

		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);

	}

	private File file;
	private FileConfiguration config;

	public void set(String path, Object obj){
		config.set(path, obj);
		try{
			config.save(file);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String path){
		return (T) config.get(path);
	}

	public void creatSection(String sectionName){
		config.createSection(sectionName);
		try{
			config.save(file);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ConfigurationSection getSection(String sectionName){
		return config.getConfigurationSection(sectionName);
	}
	
	public List<String> getStringList(String path){
		return config.getStringList(path);
	}

}
