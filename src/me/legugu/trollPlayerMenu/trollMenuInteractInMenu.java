package me.legugu.trollPlayerMenu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.legugu.trollMain;

public class trollMenuInteractInMenu implements Listener{
	
	private trollMain plg;
	private trollMenuMethoden trollMeths;
	public trollMenuInteractInMenu(trollMain plg){
		this.plg = plg;
		this.trollMeths = new trollMenuMethoden(plg);
	}
	
	@EventHandler
	public void clickInTrollMenu(InventoryClickEvent interactInInv){
		Player interactingPlayer = (Player) interactInInv.getWhoClicked();
		
		//Interact in Trollmenu
		if(interactInInv.getInventory().getName().contains(ChatColor.RED + "Menu von ")){
			interactInInv.setCancelled(true);			
			if(interactInInv.getAction() == InventoryAction.PICKUP_ALL){
				Player playerToTroll = plg.playerMenu.get(interactingPlayer);
				
				if(interactInInv.getRawSlot() == 8){
					//Close Button
					trollMeths.closeMenu(interactingPlayer);
				} else if(interactInInv.getRawSlot() == 6){
					//Inventar oeffnen
					trollMeths.openInventoryOf(interactingPlayer, playerToTroll);
				} else if(interactInInv.getRawSlot() == 4){
					//Spieler anzuenden
					playerToTroll.setFireTicks(600);
					interactingPlayer.sendMessage(plg.plgPrefix + "Du hast " + playerToTroll.getName() + " angezündet!");
					trollMeths.closeMenu(interactingPlayer);
				} else if(interactInInv.getRawSlot() == 2){
					//Spieler zu einem porten
					trollMeths.teleportPlayerToOther(interactingPlayer, playerToTroll);
				} else if(interactInInv.getRawSlot() == 0){
					playerToTroll.setHealth(0);
					interactingPlayer.sendMessage(plg.plgPrefix + "Du hast " + playerToTroll.getName() + " getötet!");
					trollMeths.closeMenu(interactingPlayer);
				} else {
					interactingPlayer.sendMessage(plg.plgPrefix + "Falscher Button! Wähle einen Button!");
				}
			}
		}
	}

}
