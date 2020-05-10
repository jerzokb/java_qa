package pl.qacourses.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1, Point p2) {
    double kw_x = (p2.x - p1.x) * (p2.x - p1.x);
    double kw_y = (p2.y - p1.y) * (p2.y - p1.y);
    return Math.sqrt(kw_x + kw_y);
  }

}
