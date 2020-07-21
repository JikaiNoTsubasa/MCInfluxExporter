package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricOnlinePlayersCount extends Metric{

	public MetricOnlinePlayersCount(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return String.valueOf(GatherManager.getOnlinePlayersCount());
	}

}
