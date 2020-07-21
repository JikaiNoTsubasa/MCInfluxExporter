package fr.triedge.minecraft.exp.conf;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.triedge.minecraft.exp.influx.DBInfo;

@XmlRootElement(name="PluginConf")
public class PluginConf {

	private DBInfo dbInfo;
	private long gatherRate = 1000L;
	
	public PluginConf() {
	}

	public DBInfo getDbInfo() {
		return dbInfo;
	}

	@XmlElement(name="DBInfo")
	public void setDbInfo(DBInfo dbInfo) {
		this.dbInfo = dbInfo;
	}

	public long getGatherRate() {
		return gatherRate;
	}

	@XmlElement(name="GatherRate")
	public void setGatherRate(long gatherRate) {
		this.gatherRate = gatherRate;
	}
}
