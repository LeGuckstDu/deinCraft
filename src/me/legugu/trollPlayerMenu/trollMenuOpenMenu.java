package me.legugu.trollPlayerMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.legugu.trollMain;

public class trollMenuOpenMenu implements CommandExecutor, Listener{

	private trollMain plg;
	private trollMenuMethoden trollMeths;
	public trollMenuOpenMenu(trollMain plg) {
		this.plg = plg;
		this.trollMeths = new trollMenuMethoden(plg);
	}
	
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("tmenu")){
			if(cmdSender instanceof Player){
				Player p = (Player) cmdSender;
				if(p.hasPermission("troll.trollmenu")){
					if(args.length == 1){
						Player playerToTroll = Bukkit.getPlayer(args[0]);
						if(playerToTroll != null){
							plg.playerMenu.put(p, playerToTroll);
							trollMeths.openMenu(p, playerToTroll);
							return true;
						} else {
							p.sendMessage(plg.plgPrefix + ChatColor.RED + "Dieser Spieler ist nicht online!");
							return true;
						}
					} else {
						p.sendMessage(plg.plgPrefix + "Nutze bitte /tmenu <Player>!");
						return true;
					}
				} else {
					p.sendMessage(plg.plgNoPermMsg);
					return true;
				}
			} else {
				cmdSender.sendMessage("! Troll-Error: Dieser Command ist nur als Spieler nutzbar!");
				return true;
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void playerInteractWithOtherPlayer(PlayerInteractEntityEvent interactEvent){
		Player interactingPlayer = interactEvent.getPlayer();
		if(interactingPlayer.hasPermission("troll.trollmenu") && interactEvent.getRightClicked() instanceof Player){
			Player playerToTroll = (Player) interactEvent.getRightClicked();
			plg.playerMenu.put(interactingPlayer, playerToTroll);
			trollMeths.openMenu(interactingPlayer, playerToTroll);
		}
	}
}
