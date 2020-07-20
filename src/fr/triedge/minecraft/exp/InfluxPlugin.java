package fr.triedge.minecraft.exp;

import java.io.File;
import java.util.logging.Level;

import javax.xml.bind.JAXBException;

import org.bukkit.plugin.java.JavaPlugin;

import fr.triedge.minecraft.exp.conf.PluginConf;
import fr.triedge.minecraft.exp.influx.DBInfo;
import fr.triedge.minecraft.exp.influx.DBManager;
import fr.triedge.minecraft.exp.task.TaskExport;
import fr.triedge.minecraft.exp.utils.Utils;

public class InfluxPlugin extends JavaPlugin{
	
	public static final String CONF_FILE							= "plugins/MCInfluxExporter/config.xml";
	private PluginConf pluginConfig;
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
		
		// Setup scheduler
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TaskExport(this), 0L, 1000L);
	}

	private void loadConfiguration() {
		File file = new File(CONF_FILE);
		if (!file.getParentFile().exists()) {
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
	
}
