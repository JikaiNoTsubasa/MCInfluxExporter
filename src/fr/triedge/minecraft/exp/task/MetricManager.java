package fr.triedge.minecraft.exp.task;

import java.util.ArrayList;

import fr.triedge.minecraft.exp.InfluxPlugin;
import fr.triedge.minecraft.exp.influx.Metric;

public class MetricManager {

	private InfluxPlugin plugin;
	private ArrayList<Metric> metrics = new ArrayList<>();
	
	public MetricManager(InfluxPlugin plugin) {
		setPlugin(plugin);
	}
	
	public void registerMetric(Metric metric) {
		getMetrics().add(metric);
		getPlugin().getLogger().info("Registered metric "+metric.getName());
	}

	public ArrayList<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(ArrayList<Metric> metrics) {
		this.metrics = metrics;
	}

	public InfluxPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(InfluxPlugin plugin) {
		this.plugin = plugin;
	}
}
