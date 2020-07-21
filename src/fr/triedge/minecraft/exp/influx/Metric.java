package fr.triedge.minecraft.exp.influx;

public abstract class Metric {

	public static final String HEAP_USAGE						= "mc_heap_usage";
	public static final String HEAP_MAX 						= "mc_heap_max";
	public static final String HEAP_PERCENT						= "mc_heap_percent";
	public static final String ONLINE_PLAYERS_COUNT				= "mc_players_count";
	public static final String ONLINE_PLAYERS_LIST				= "mc_players_list";
	public static final String LIVING_ENTITIES					= "mc_living_entities";
	
	private String name, cachedValue;
	
	public Metric(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		String val = processValue();
		setCachedValue(val);
		return val;
	}
	
	protected abstract String processValue();

	public String getCachedValue() {
		return cachedValue;
	}

	public void setCachedValue(String cachedValue) {
		this.cachedValue = cachedValue;
	}
	
}
