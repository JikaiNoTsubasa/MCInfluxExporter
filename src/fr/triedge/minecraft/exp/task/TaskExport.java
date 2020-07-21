package fr.triedge.minecraft.exp.task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
		ArrayList<Metric> metrics = getPlugin().getMetricManager().getMetrics();
		for (Metric m : metrics) {
			String val = m.getValue();
			String name = m.getName();
			try {
				getPlugin().getLogger().log(Level.INFO, getPlugin().getDbManager().storeMetric(name,val));
			} catch (URISyntaxException | IOException e) {
				getPlugin().getLogger().log(Level.SEVERE,"Cannot store data into influxDB",e);
			}
		}
	}

	public InfluxPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(InfluxPlugin plugin) {
		this.plugin = plugin;
	}

}
