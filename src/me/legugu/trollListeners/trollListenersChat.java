package me.legugu.trollListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.legugu.trollMain;

public class trollListenersChat implements Listener{
	
	private trollMain plg;
	public trollListenersChat(trollMain plg){
		this.plg = plg;
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent chatEvent){
		Player chattingPlayer = chatEvent.getPlayer();
		
		//Renaming Player
		int randomNameIndex = (int) ((Math.random() * plg.randomNames.size()) + 1);
		if(plg.playerHaveToRename.contains(chattingPlayer)){
			if(!plg.randomNames.isEmpty()){
				String randomName = plg.randomNames.get(randomNameIndex);
				chattingPlayer.setDisplayName(randomName);
			}
		}
		
		
	}

}
