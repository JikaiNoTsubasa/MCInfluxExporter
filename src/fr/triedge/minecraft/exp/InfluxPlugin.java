package fr.triedge.minecraft.exp;

import java.io.File;
import java.util.logging.Level;

import javax.xml.bind.JAXBException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.triedge.minecraft.exp.conf.PluginConf;
import fr.triedge.minecraft.exp.influx.DBInfo;
import fr.triedge.minecraft.exp.influx.DBManager;
import fr.triedge.minecraft.exp.influx.Metric;
import fr.triedge.minecraft.exp.influx.MetricHeapMax;
import fr.triedge.minecraft.exp.influx.MetricHeapPercent;
import fr.triedge.minecraft.exp.influx.MetricHeapUsage;
import fr.triedge.minecraft.exp.influx.MetricLivingEntitiesCount;
import fr.triedge.minecraft.exp.influx.MetricOnlinePlayersCount;
import fr.triedge.minecraft.exp.task.MetricManager;
import fr.triedge.minecraft.exp.task.TaskExport;
import fr.triedge.minecraft.exp.utils.Utils;

public class InfluxPlugin extends JavaPlugin{
	
	public static final String CONF_FILE							= "plugins/MCInfluxExporter/config.xml";
	private PluginConf pluginConfig;
	private MetricManager metricManager;
	private DBManager dbManager;

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		
		// Load config
		loadConfiguration();
		
		// Set db
		setDbManager(new DBManager(getPluginConfig().getDbInfo()));
		
		// Register default metrics
		loadDefaultMetrics();
		
		// Setup scheduler
		long rate = getPluginConfig().getGatherRate();
		if (rate < 500L)
			rate = 1000L;
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TaskExport(this), 0L, rate);
	}
	
	private void loadDefaultMetrics() {
		setMetricManager(new MetricManager(this));
		MetricHeapUsage mhu = new MetricHeapUsage(Metric.HEAP_USAGE);
		MetricHeapMax mhm = new MetricHeapMax(Metric.HEAP_MAX);
		MetricHeapPercent mhp = new MetricHeapPercent(Metric.HEAP_PERCENT);
		MetricOnlinePlayersCount mopc = new MetricOnlinePlayersCount(Metric.ONLINE_PLAYERS_COUNT);
		MetricLivingEntitiesCount mlec = new MetricLivingEntitiesCount(Metric.LIVING_ENTITIES);
		
		getMetricManager().registerMetric(mhu);
		getMetricManager().registerMetric(mhm);
		getMetricManager().registerMetric(mhp);
		getMetricManager().registerMetric(mopc);
		getMetricManager().registerMetric(mlec);
	}

	private void loadConfiguration() {
		File file = new File(CONF_FILE);
		if (!file.exists() || !file.getParentFile().exists()) {
			getLogger().warning("Plugin folder doesn't exist!");
			file.getParentFile().mkdirs();
			getLogger().info("Created path: "+file.getParentFile().getAbsolutePath());
		}
		if (!file.exists()) {
			getLogger().warning("Configuration file doens't exist");
			setPluginConfig(new PluginConf());
			getPluginConfig().setDbInfo(new DBInfo());
			try {
				Utils.storeXml(getPluginConfig(), file);
				getLogger().info("Created default config in: "+file.getAbsolutePath());
			} catch (JAXBException e) {
				getLogger().log(Level.SEVERE,"Failed to create default config",e);
			}
		}else {
			getLogger().info("Loading configuration file: "+file.getAbsolutePath());
			try {
				PluginConf conf = Utils.loadXml(PluginConf.class, file);
				setPluginConfig(conf);
				getLogger().info("Configuration loaded");
			} catch (JAXBException e) {
				getLogger().log(Level.SEVERE,"Failed to load config",e);
			}
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	public PluginConf getPluginConfig() {
		return pluginConfig;
	}

	public void setPluginConfig(PluginConf pluginConfig) {
		this.pluginConfig = pluginConfig;
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public MetricManager getMetricManager() {
		return metricManager;
	}

	public void setMetricManager(MetricManager metricManager) {
		this.metricManager = metricManager;
	}
	
}
