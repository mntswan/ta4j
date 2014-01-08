package eu.verdelhan.tailtest.indicator.tracker;

import eu.verdelhan.tailtest.Indicator;
import eu.verdelhan.tailtest.indicator.cache.CachedIndicator;

public class SMA extends CachedIndicator<Double> {

	private final Indicator<? extends Number> indicator;

	private final int timeFrame;

	public SMA(Indicator<? extends Number> indicator, int timeFrame) {
		this.indicator = indicator;
		this.timeFrame = timeFrame;
	}

	protected Double calculate(int index) {
		double sum = 0.0;
		for (int i = Math.max(0, index - timeFrame + 1); i <= index; i++) {
			sum += indicator.getValue(i).doubleValue();
		}

		return sum / Math.min(timeFrame, index + 1);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " timeFrame: " + timeFrame;
	}

}
