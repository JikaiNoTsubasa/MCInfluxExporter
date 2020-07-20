package fr.triedge.minecraft.exp.influx;

public class Metric {

	public static final String HEAP_USAGE						= "mc_heap_usage";
	public static final String HEAP_MAX 						= "mc_heap_max";
	public static final String HEAP_PERCENT						= "mc_heap_percent";
	public static final String ONLINE_PLAYERS_COUNT				= "mc_players_count";
	public static final String ONLINE_PLAYERS_LIST				= "mc_players_list";
	
	private String name, value;
	
	public Metric(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
