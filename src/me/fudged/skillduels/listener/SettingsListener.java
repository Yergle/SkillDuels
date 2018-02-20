package me.fudged.skillduels.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import me.fudged.skillduels.SkillDuels;
import me.fudged.skillduels.arena.Arena;
import me.fudged.skillduels.arena.DuelSettings;
import net.md_5.bungee.api.ChatColor;

public class SettingsListener implements Listener {

	public SettingsListener() {
		Bukkit.getServer().getPluginManager().registerEvents(this, SkillDuels.getInst());
	}

	// Natural Regeneration
	@EventHandler(priority = EventPriority.LOWEST)
	public void onRegen(EntityRegainHealthEvent event){
		if(!(event.getEntity() instanceof Player)){
			return;
		}

		Player player = (Player) event.getEntity();
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(player.getUniqueId());
		if(arena == null){
			return;
		}
		if(arena.getSettings().contains(DuelSettings.REGEN)){
			if(event.getRegainReason() == RegainReason.REGEN || event.getRegainReason() == RegainReason.SATIATED){
				event.setCancelled(true);;
			}
		}

	}

	// Enderpearl
	@EventHandler(priority = EventPriority.LOWEST)
	public void onTeleport(PlayerTeleportEvent event){
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId());
		if(arena == null){
			return;
		}

		if(arena.getSettings().contains(DuelSettings.ENDERPEARL)){
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.YELLOW + "Enderpearls are disabled in this duel");
			event.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
		}

	}

	// Food loss
	@EventHandler(priority = EventPriority.LOWEST)
	public void onHungerChange(FoodLevelChangeEvent event){
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		Player player = (Player) event.getEntity();
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(player.getUniqueId());
		if(arena == null){
			return;
		}

		if(arena.getSettings().contains(DuelSettings.HUNGER)){
			event.setCancelled(true);
		}

	}

	// Infinite durability
	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemBreak(PlayerItemBreakEvent event){
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId());
		if(arena == null){
			return;
		}
		if(arena.getSettings().contains(DuelSettings.DURABILITY)){
			event.getBrokenItem().setAmount(1);
		}

	}

	// Potion consume
	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemConsume(PlayerItemConsumeEvent event){
		if(!(event.getItem().getType() == Material.POTION)){
			return;
		}
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId());
		if(arena == null){
			return;
		}
		if(arena.getSettings().contains(DuelSettings.POTIONS)){
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.YELLOW + "Potions are disabled in this duel");
		}
	}
	// Splash Potion
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPotionSplash(PotionSplashEvent event){
		ProjectileSource source = event.getEntity().getShooter();
		if(!(source instanceof Player)){
			return;
		}
		Player player = (Player) source;
		Arena arena = SkillDuels.getInst().getArenaManager().getArena(player.getUniqueId());
		if(arena == null){
			return;
		}
		
		if(arena.getSettings().contains(DuelSettings.POTIONS)){
			event.setCancelled(true);
			player.sendMessage(ChatColor.YELLOW + "Potions are disabled in this duel");
			player.getInventory().addItem(event.getEntity().getItem());
		}
	}

}
