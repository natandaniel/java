package structural.adapter;

import java.awt.*;

public class MetricSystem {
  public double getDistance(Point aMetric, Point bMetric) {
    return Math.sqrt(Math.pow(aMetric.x - bMetric.x, 2) + Math.pow(aMetric.y - bMetric.y, 2));
  }
}