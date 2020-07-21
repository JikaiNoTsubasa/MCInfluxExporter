package fr.triedge.minecraft.exp.influx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

public class DBManager {

	private DBInfo dbInfo;
	
	public DBManager(DBInfo dbInfo) {
		super();
		this.dbInfo = dbInfo;
	}
	
	public String storeMetric(String name, String value) throws URISyntaxException, IOException {
		HttpResponse response = sendPost(name+" value="+value);
		int code = response.getCode();
		Header[] headers = response.getHeaders();
		StringBuilder tmp = new StringBuilder();
		tmp.append("[");
		tmp.append(code);
		tmp.append("] ");
		tmp.append("[");
		for (Header h : headers) {
			tmp.append(h.getName());
			tmp.append("=");
			tmp.append(h.getValue());
			tmp.append(",");
		}
		tmp.append("] ");
		tmp.append("[");
		tmp.append(response.getReasonPhrase());
		tmp.append("]");
		return tmp.toString();
	}
	
	public String storeMetric(Metric metric) throws URISyntaxException, IOException {
		return storeMetric(metric.getName(), metric.getCachedValue());
	}
	
	private HttpResponse sendPost(String body) throws URISyntaxException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost(getDbInfo().getHost())
		        .setPort(Integer.parseInt(getDbInfo().getPort()))
		        .setPath("/write")
		        .setParameter("db", getDbInfo().getDatabase())
		        .setParameter("u", getDbInfo().getUsername())
		        .setParameter("p", getDbInfo().getPassword())
		        .build();
		HttpPost httppost = new HttpPost(uri);
		StringEntity entity = new StringEntity(body, ContentType.create("plain/text"));
		httppost.setEntity(entity);
		
		return httpclient.execute(httppost);
		
	}

	public DBInfo getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(DBInfo dbInfo) {
		this.dbInfo = dbInfo;
	}
}
