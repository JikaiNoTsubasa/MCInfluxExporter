package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricHeapPercent extends Metric{

	public MetricHeapPercent(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return String.valueOf(GatherManager.getHeapUsagePercent());
	}

}
