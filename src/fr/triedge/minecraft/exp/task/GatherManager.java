package fr.triedge.minecraft.exp.task;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GatherManager {

	public static String getOnlinePlayers() {
		if (Bukkit.getOnlinePlayers().size() > 0) {
			String list = "";
			for (Player p : Bukkit.getOnlinePlayers()) {
				list += p.getName()+",";
			}
			return list;
		}else {
			return "-";
		}
	}
	
	public static int getOnlinePlayersCount() {
		return Bukkit.getOnlinePlayers().size();
	}

	public static double getHeapUsagePercent() {
		MemoryUsage mem = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		return (mem.getUsed()*100)/mem.getMax();
	}
	
	public static double getHeapUsage() {
		MemoryUsage mem = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		return mem.getUsed()/1048576;
	}
	
	public static double getHeapMax() {
		MemoryUsage mem = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		return mem.getMax()/1048576;
	}
	
}
