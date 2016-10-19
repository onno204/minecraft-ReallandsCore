package org.eu.nl.onno204.Core.Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.eu.nl.onno204.Core.Main.Holder;
import org.eu.nl.onno204.Core.Main.Methods;

public class Setup {

	public static HashMap<String, File> Files = new HashMap<String, File>();
	
	public static void Load(){
		AddFilesToList();
		CreateFiles();
		LoadConfigs();
	}
	
	
	public static void AddFilesToList(){
		Files.put( "CommandWatcher", new File(Holder.pl.getDataFolder(), "CommandWatcher.yml") );
		Files.put( "Bank", new File(Holder.pl.getDataFolder(), "Bank.yml") );
	}
	
	public static void CreateFiles(){
		for (File file : Files.values()){
			try { 
				if (file.createNewFile()) { Holder.Console.sendMessage(Holder.title + "File '" + file.getName() + "' is created!");} 
			} catch (IOException e) { Methods.NotifyPlayerWithPerms(Holder.title + "Something went wrong while creating files");  }
		}
	}
	
	public static void LoadConfigs(){
		for (String s : Files.keySet()){
			if(s.equalsIgnoreCase("CommandWatcher")){
				Holder.Console.sendMessage(Holder.title + "Loaded CommandWatcher.");
				Config.CommandWatcher = YamlConfiguration.loadConfiguration( Files.get(s) );
			}else if(s.equalsIgnoreCase("Bank")){
				Config.Bank = YamlConfiguration.loadConfiguration( Files.get(s) );
				Holder.Console.sendMessage(Holder.title + "Loaded Bank.");
			}
		}
	}

	
	public static void SafeConfigs(){
		Holder.Console.sendMessage(Holder.title + "Saving configs....");
		for (String s : Files.keySet()){
			if(s.equalsIgnoreCase("CommandWatcher")){
				try { Config.CommandWatcher.save( Files.get(s) );
				} catch (IOException e) { Methods.NotifyPlayerWithPerms(Holder.title + "Something went wrong while saving commanwatcher config"); }
				Holder.Console.sendMessage(Holder.title + "Saved CommandWatcher.");
			}else if(s.equalsIgnoreCase("Bank")){
				try { Config.Bank.save( Files.get(s) );
				} catch (IOException e) { Methods.NotifyPlayerWithPerms(Holder.title + "Something went wrong while saving Bank config"); }
				Holder.Console.sendMessage(Holder.title + "Saved Bank.");
				Holder.Console.sendMessage(Holder.title + "Saved configs.");
			}
		}
	}
	
	
	
	
	
}
