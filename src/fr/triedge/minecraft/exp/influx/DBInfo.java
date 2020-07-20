package fr.triedge.minecraft.exp.influx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DBInfo")
public class DBInfo {

	private String host="localhost", port="8086", database="mcmetrics", username="mcadmin", password;
	
	public DBInfo() {
	}

	public String getHost() {
		return host;
	}

	@XmlElement(name="Host")
	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	@XmlElement(name="Port")
	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	@XmlElement(name="Database")
	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement(name="Username")
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement(name="Password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
