package eu.verdelhan.tailtest.indicator.tracker;

import eu.verdelhan.tailtest.Indicator;
import eu.verdelhan.tailtest.indicator.cache.CachedIndicator;

//TODO: pequena explicacao
public class EMA extends CachedIndicator<Double> {

	private final Indicator<? extends Number> indicator;

	private final int timeFrame;

	public EMA(Indicator<? extends Number> indicator, int timeFrame) {
		this.indicator = indicator;
		this.timeFrame = timeFrame;
	}

	private double multiplier() {
		return 2 / (double) (timeFrame + 1);
	}

	protected Double calculate(int index) {
		if (index + 1 < timeFrame)
			return new SMA(indicator, timeFrame).getValue(index);

		if(index == 0)
			return indicator.getValue(0).doubleValue();
		double emaPrev = getValue(index - 1).doubleValue();
		return ((indicator.getValue(index).doubleValue() - emaPrev) * multiplier()) + emaPrev;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " timeFrame: " + timeFrame;
	}
}
