package com.blalp.flyspeed;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlySpeed extends JavaPlugin {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equals("flyspeed")&&sender.hasPermission("fly.speed")){
			if(args.length==0){
				sender.sendMessage("You need an arg.");
			} else {
				((Player)sender).setFlySpeed(Float.parseFloat(args[0]));
			}
			return true;
		}
		return false;
	}
}