package structural.adapter;

import java.awt.*;

public class MetricToImperialAdapter implements ImperialSystem {
  private MetricSystem metricSystem;

  public MetricToImperialAdapter(MetricSystem metricSystem) {
    this.metricSystem = metricSystem;
  }

  @Override
  public double getDistanceInFeet(Point aMetric, Point bMetric) {
    return metricSystem.getDistance(aMetric, bMetric) * 3.28084;
  }
}