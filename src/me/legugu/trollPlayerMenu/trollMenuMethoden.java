package me.legugu.trollPlayerMenu;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.legugu.trollMain;

public class trollMenuMethoden {

	private trollMain plg;
	public trollMenuMethoden(trollMain plg){
		this.plg = plg;
	}
	
	public void openMenu(Player playerWhoHaveToSeeMenu, Player playerToTroll){
		playerToTroll = plg.playerMenu.get(playerWhoHaveToSeeMenu);
		Inventory trollMenu = playerWhoHaveToSeeMenu.getServer().createInventory(null, 9, ChatColor.RED + "Menu von " + playerToTroll.getName() + "!");
		
		//Items in Menu
		//Close Button (Slot 9)
		ItemStack closeButton = new ItemStack(Material.BARRIER, 1);
		ItemMeta closeMeta = closeButton.getItemMeta();
		closeMeta.setDisplayName(ChatColor.DARK_RED + "Menu schließen!");
		closeButton.setItemMeta(closeMeta);
		
		//Spieler Invetar von Spieler öffnen (Slot 7)
		ItemStack inventoryButton = new ItemStack(Material.CHEST, 1);
		ItemMeta inventoryMeta = inventoryButton.getItemMeta();
		inventoryMeta.setDisplayName(ChatColor.BLUE + "Inventar des Spielers öffnen!");
		inventoryButton.setItemMeta(inventoryMeta);
		
		//Spieler anzuenden (Slot 5)
		ItemStack burnButton = new ItemStack(Material.FLINT_AND_STEEL, 1);
		ItemMeta burnMeta = burnButton.getItemMeta();
		burnMeta.setDisplayName(ChatColor.RED + "Spieler anzünden!");
		burnButton.setItemMeta(burnMeta);
		
		//TP zu Admin (Slot 3)
		ItemStack teleportButton = new ItemStack(Material.COMPASS, 1);
		ItemMeta teleportMeta = teleportButton.getItemMeta();
		teleportMeta.setDisplayName(ChatColor.GOLD + "Spieler zu dir teleportieren!");
		teleportButton.setItemMeta(teleportMeta);
		
		//Slay Button (Slot 1)
		ItemStack slayButton = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
		ItemMeta slayMeta = slayButton.getItemMeta();
		slayMeta.setDisplayName(ChatColor.RED + "Spieler toeten!");
		slayButton.setItemMeta(slayMeta);
		
		//Items in Menu setzen
		trollMenu.setItem(8, closeButton);
		trollMenu.setItem(6, inventoryButton);
		trollMenu.setItem(4, burnButton);
		trollMenu.setItem(2, teleportButton);
		trollMenu.setItem(0, slayButton);
		
		//Leere Slots fuellen
		for(int i = 0; i < 9; i++){
			if(trollMenu.getItem(i) == null){
				ItemStack glassToFillEmptySlots = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
				ItemMeta glassMeta = glassToFillEmptySlots.getItemMeta();
				glassMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Nichts :P");
				glassToFillEmptySlots.setItemMeta(glassMeta);
				trollMenu.setItem(i, glassToFillEmptySlots);
			}
		}
		
		playerWhoHaveToSeeMenu.openInventory(trollMenu);
		playerWhoHaveToSeeMenu.sendMessage(plg.plgPrefix + "Öffne Troll-Menu für Spieler " + playerToTroll.getName() + "!");
	}
	
	public void closeMenu(Player playerWhoSeeMenu){
		playerWhoSeeMenu.closeInventory();
		plg.playerMenu.remove(playerWhoSeeMenu);
	}
	
	public void openInventoryOf(Player playerSeeInventory, Player inventoryPlayer){
		inventoryPlayer = plg.playerMenu.get(playerSeeInventory);
		closeMenu(playerSeeInventory);
		
		Inventory otherInv = inventoryPlayer.getInventory();
		playerSeeInventory.openInventory(otherInv);
	}
	
	public void teleportPlayerToOther(Player playerWhoTrolls, Player playerToTroll){
		Location playerWhoTrollsLocation = playerWhoTrolls.getLocation();
		playerToTroll.teleport(playerWhoTrollsLocation);
		playerWhoTrolls.sendMessage(plg.plgPrefix + "Du hast " + playerToTroll.getName() + " zu dir teleportiert!");
		closeMenu(playerWhoTrolls);
	}
	
}
