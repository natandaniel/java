package structural.adapter;

import java.awt.*;

public class Client {

  public static void main(String[] args) {
    Point originMetric = new Point(0, 0);
    Point targetMetric = new Point(0, 10);

    double distanceInFeet =
        getDistanceInFeet(
            new MetricToImperialAdapter(new MetricSystem()),
            originMetric,
            targetMetric);

    System.out.println("distance in feet: " + distanceInFeet + "f");
  }

  private static double getDistanceInFeet(
      ImperialSystem imperialSystem, Point aMetric, Point bMetric) {
    return imperialSystem.getDistanceInFeet(aMetric, bMetric);
  }
}
