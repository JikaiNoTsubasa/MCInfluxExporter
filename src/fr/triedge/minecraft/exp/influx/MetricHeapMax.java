package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricHeapMax extends Metric{

	public MetricHeapMax(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return String.valueOf(GatherManager.getHeapMax());
	}

}
