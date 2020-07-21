package fr.triedge.minecraft.exp.influx;

import fr.triedge.minecraft.exp.task.GatherManager;

public class MetricLivingEntitiesCount extends Metric{

	public MetricLivingEntitiesCount(String name) {
		super(name);
	}

	@Override
	protected String processValue() {
		return String.valueOf(GatherManager.getEntities());
	}

}
