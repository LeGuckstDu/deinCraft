package me.legugu.trollCommands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.legugu.trollMain;
import net.md_5.bungee.api.ChatColor;

public class trollCommandsExportAndCo implements CommandExecutor{

	private trollMain plg;
	public trollCommandsExportAndCo(trollMain plg) {
		this.plg = plg;
	}
	
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("export")){
			if(cmdSender instanceof Player){
				Player p = (Player) cmdSender;
				if(p.hasPermission("troll.export")){
					//Standard ist 12, bei Eingabe oder zu hoher Eingabe wieder 12!
					int exportRadius = 12;
					if(args.length == 1){
						exportRadius = Integer.parseInt(args[0]);
					}
					if(exportRadius >= 250){
						exportRadius = 12;
					}
					
					List<Entity> entitiesNearToPlayer = p.getNearbyEntities(exportRadius, exportRadius / 2 + 1, exportRadius);
					Location playerLocation = p.getLocation();
					StringBuilder exportedPlayers = new StringBuilder();
					boolean isFirstPlayerOnList = true;
					
					//Exporten
					for(Entity entityToExport : entitiesNearToPlayer){
						if(entityToExport instanceof Player){
							Location entityLocation = entityToExport.getLocation();
							Vector vectorAway = entityLocation.toVector().subtract(entityLocation.toVector());
							double vSpeedToExport = 1.0D;
							vectorAway.setY(vSpeedToExport);
							entityToExport.setVelocity(vectorAway);
							
							if(entityToExport instanceof Player){
								if(!isFirstPlayerOnList){
									exportedPlayers.append(",");
								}
								Player entityPlayer = (Player) entityToExport;
								exportedPlayers.append(entityPlayer.getName());
								isFirstPlayerOnList = false;
							}
						} else {
							Location entityLocation = entityToExport.getLocation();
							Vector vectorAway = entityLocation.toVector().subtract(entityLocation.toVector());
							double vSpeedToExport = 1.0D;
							vectorAway.setY(vSpeedToExport);
							entityToExport.setVelocity(vectorAway);
							
							if(entityToExport instanceof Player){
								if(!isFirstPlayerOnList){
									exportedPlayers.append(",");
								}
								Player entityPlayer = (Player) entityToExport;
								exportedPlayers.append(entityPlayer.getName());
								isFirstPlayerOnList = false;
							}
						}
					}
					if(exportedPlayers.length() == 0){
						p.sendMessage(plg.plgPrefix + ChatColor.RED + "Es ist kein Spieler in deiner Nähe!");
						return true;
					} else {
						p.sendMessage(plg.plgPrefix + "Du hast folgende Spieler in einem Radius von " + exportRadius + " 'exportiert': " + exportedPlayers);
						return true;
					}
				}
			} else {
				cmdSender.sendMessage("! Troll-Error: Dieser Command ist nur von Spielern ausfuehrbar!");
				return true;
			}
		}
		return false;
	}

}
