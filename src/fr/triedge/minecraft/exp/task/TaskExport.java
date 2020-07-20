package fr.triedge.minecraft.exp.task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;

import fr.triedge.minecraft.exp.InfluxPlugin;
import fr.triedge.minecraft.exp.influx.Metric;

public class TaskExport implements Runnable{

	private InfluxPlugin plugin;
	
	public TaskExport(InfluxPlugin plugin) {
		this.setPlugin(plugin);
	}
	
	@Override
	public void run() {
		getPlugin().getLogger().log(Level.INFO,"Gathering metrics...");
		String HU = String.valueOf(GatherManager.getHeapUsage());
		String HM = String.valueOf(GatherManager.getHeapMax());
		String HP = String.valueOf(GatherManager.getHeapUsagePercent());
		String OPc = String.valueOf(GatherManager.getOnlinePlayersCount());
		Metric mHU = new Metric(Metric.HEAP_USAGE,HU);
		Metric mHM = new Metric(Metric.HEAP_MAX,HM);
		Metric mHP = new Metric(Metric.HEAP_PERCENT,HP);
		Metric mOPc = new Metric(Metric.ONLINE_PLAYERS_COUNT,OPc);
		
		getPlugin().getLogger().log(Level.INFO,"Storing metrics...");
		try {
			getPlugin().getLogger().log(Level.INFO, getPlugin().getDbManager().storeMetric(mHU));
			getPlugin().getLogger().log(Level.INFO, getPlugin().getDbManager().storeMetric(mHM));
			getPlugin().getLogger().log(Level.INFO, getPlugin().getDbManager().storeMetric(mHP));
			getPlugin().getLogger().log(Level.INFO, getPlugin().getDbManager().storeMetric(mOPc));
		} catch (URISyntaxException | IOException e) {
			getPlugin().getLogger().log(Level.SEVERE,"Cannot store data into influxDB",e);
		}
		
	}

	public InfluxPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(InfluxPlugin plugin) {
		this.plugin = plugin;
	}

}
