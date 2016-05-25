package me.legugu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.legugu.trollCommands.trollCommandsExportAndCo;
import me.legugu.trollCommands.trollCommandsNames;
import me.legugu.trollListeners.trollListenersChat;
import me.legugu.trollPlayerMenu.trollMenuInteractInMenu;
import me.legugu.trollPlayerMenu.trollMenuOpenMenu;
import net.md_5.bungee.api.ChatColor;

public class trollMain extends JavaPlugin{
	
	//Vars and Co
	public static trollMain instance;
	public Logger trollLog = this.getLogger();
	
	public String plgPrefix = ChatColor.GRAY +"[" + ChatColor.RED + "Troll" + ChatColor.GRAY + "] ";
	public String plgNoPermMsg = plgPrefix + ChatColor.RED + "Du hast keine Berechtigung dies zu tun!";
	
	//File, Array, ArrayList and HashMap for Random-Names
	public File randomNamesFile = new File("plugins/Troll/", "names.txt");
	public ArrayList<String> randomNames = new ArrayList<>(); 
	public ArrayList<Player> playerHaveToRename = new ArrayList<>();
	public HashMap<Player, String> normalPlayerName = new HashMap<>();
	
	//TrollMenu
	public HashMap<Player, Player> playerMenu = new HashMap<>();
	
	//Dir
	File trollDir = new File("plugins/Troll");
	
	//Enable and Disabled
	public void onEnable(){
		trollLog.log(Level.INFO, "Let's troll! Troll-Plugin enabled! (Version: " + this.getDescription().getVersion() + ")");
		if(!trollDir.exists()){
			trollDir.mkdir();
		}
		registerCommandsAndListeners();
		checkRandomNamesFile();
		loadFakeNicknames();
	}
	
	public void onDisable(){
		trollLog.log(Level.INFO, "Troll-Plugin disabled! (Version: " + this.getDescription().getVersion() + ")");
	}
	
	//Other
	
	//Loading Commands and Listeners
	public void registerCommandsAndListeners(){
		trollLog.log(Level.INFO, "Loading commands and listeners...");

		//Random-Rename Command
		getCommand("rname").setExecutor(new trollCommandsNames(this));
		
		//Export CMD
		getCommand("export").setExecutor(new trollCommandsExportAndCo(this));
		
		//TMenu CMD
		getCommand("tmenu").setExecutor(new trollMenuOpenMenu(this));
		
		//Listeners
		Bukkit.getServer().getPluginManager().registerEvents(new trollListenersChat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new trollMenuOpenMenu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new trollMenuInteractInMenu(this), this);
		trollLog.log(Level.INFO, "Commands and listeners loaded!");
	}
	
	//Loading FakeNickNames as Strings in Array
	public void loadFakeNicknames(){
			if(randomNamesFile.exists()){
				try {
					BufferedReader trollReader = new BufferedReader(new FileReader(randomNamesFile));
					String readedLine;
					try {
						while((readedLine = trollReader.readLine()) != null){
							randomNames.add(readedLine);
						}
						trollReader.close();
						trollLog.log(Level.INFO, "! Randomnames geladen! Es wurden " + randomNames.size() + " Namen geladen!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				trollLog.log(Level.INFO, "! Random Names nicht geladen! (Keine Datei vorhanden!)");
			}
	}
	
	public void checkRandomNamesFile(){
		if(randomNamesFile.exists()){
			trollLog.log(Level.INFO, "[*] Randomnames-Datei gefunden!");
		} else {
			trollLog.log(Level.WARNING, "[*] Konnte Random-Names nicht finden, erstelle neue Datei!");
			try {
				randomNamesFile.createNewFile();
				trollLog.log(Level.INFO, "[*] Random-Names-File erstellt!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
