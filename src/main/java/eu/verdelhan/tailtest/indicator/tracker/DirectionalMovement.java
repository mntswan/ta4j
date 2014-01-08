package eu.verdelhan.tailtest.indicator.tracker;

import eu.verdelhan.tailtest.Indicator;
import eu.verdelhan.tailtest.TimeSeries;
import eu.verdelhan.tailtest.indicator.helper.DirectionalDown;
import eu.verdelhan.tailtest.indicator.helper.DirectionalUp;

public class DirectionalMovement implements Indicator<Double>{

	
	private final int timeFrame;
	private final DirectionalUp dup;
	private final DirectionalDown ddown;
	public DirectionalMovement(TimeSeries series, int timeFrame) {
		this.timeFrame = timeFrame;
		dup = new DirectionalUp(series, timeFrame);
		ddown = new DirectionalDown(series, timeFrame);
	}

	@Override
	public Double getValue(int index) {
		double dupValue = dup.getValue(index);
		double ddownValue = ddown.getValue(index);
		
		return (Math.abs((dupValue - ddownValue)) / (dupValue + ddownValue)) * 100;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " timeFrame: " + timeFrame;
	}
}
