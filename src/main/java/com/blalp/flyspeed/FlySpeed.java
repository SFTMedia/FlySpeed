package com.blalp.flyspeed;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlySpeed extends JavaPlugin {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equals("flyspeed")&&sender.hasPermission("fly.speed")){
			if(args.length==0){
				sender.sendMessage("Specify how much faster 1 to 10 (you can also do negative, but not reccomended xD)");
				sender.sendMessage("Usage: to increase flight speed 2x /flyspeed 2");
			} else {
				try {
					if (Float.parseFloat(args[0])<-10||Float.parseFloat(args[0])>10){
						sender.sendMessage("The max is a speed multiplier of 10");
					}
				} catch (Exception e){
					sender.sendMessage("Please specify a number.");
				}
				((Player)sender).setFlySpeed(Float.parseFloat(args[0])/10);
				sender.sendMessage("Set flyspeed to "+Float.parseFloat(args[0])/10+"x normal");
			}
			return true;
		}
		return false;
	}
}