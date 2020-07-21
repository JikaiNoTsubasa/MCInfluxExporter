package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricHeapUsage extends Metric{

	public MetricHeapUsage(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return String.valueOf(GatherManager.getHeapUsage());
	}

}
