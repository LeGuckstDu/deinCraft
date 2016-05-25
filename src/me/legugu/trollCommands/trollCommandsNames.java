package me.legugu.trollCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.legugu.trollMain;

public class trollCommandsNames implements CommandExecutor{

	private trollMain plg;
	public trollCommandsNames(trollMain plg) {
		this.plg = plg;
	}
	
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("rname")){
			if(cmdSender instanceof Player){
				Player p = (Player) cmdSender;
				if(p.hasPermission("troll.randomname")){
					if(args.length == 1){
						Player playerToRename = Bukkit.getPlayer(args[0]);
						if(playerToRename != null){
							if(!plg.playerHaveToRename.contains(playerToRename)){
								plg.playerHaveToRename.add(playerToRename);
								plg.normalPlayerName.put(playerToRename, playerToRename.getName());
								p.sendMessage(plg.plgPrefix + "Rename-Mode für Spieler " + playerToRename.getName() + " aktiviert!");
								return true;
							} else {
								plg.playerHaveToRename.remove(playerToRename);
								playerToRename.setDisplayName(plg.normalPlayerName.get(playerToRename));
								plg.normalPlayerName.remove(playerToRename);
								p.sendMessage(plg.plgPrefix + "Rename-Mode für Spieler " + playerToRename.getName() + " deaktiviert!");
								return true;
							}
						} else {
							p.sendMessage(plg.plgPrefix + "Dieser Spieler ist nicht online!");
							return true;
						}
					} else {
						p.sendMessage(plg.plgPrefix + "Nutze bitte /rname <Player>!");
						return true;
					}
				} else {
					p.sendMessage(plg.plgNoPermMsg);
					return true;
				}
			} else {
				cmdSender.sendMessage("! Troll-Error: Dieser Command ist nur von Spielern ausfuehrbar!");
				return true;
			}
		}
		
		return false;
	}

}
