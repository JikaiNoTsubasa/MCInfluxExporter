package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricOnlinePlayers extends Metric{

	public MetricOnlinePlayers(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return GatherManager.getOnlinePlayers();
	}

}
