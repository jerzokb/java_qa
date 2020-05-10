package pl.qacourses.sandbox;

public class MyFirstProgram {

	  public static void main(String[] args) {

		hello("world");
		hello("Beata");

		Square s = new Square(5);
		System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Powierzchnia prostokata o bokach " + r.a + " i " + r.b + " = " + r.area());

		Point p1 = new Point(0,0);
		Point p2 = new Point(2,3);
		System.out.println("Distance between points = " + p1.distance(p1, p2));
	}

	public static void hello(String somebody) {
		System.out.println("Hello " + somebody);
	}
/*
	public static double distance(Point p1, Point p2) {
		double kw_x = (p2.x - p1.x) * (p2.x - p1.x);
		double kw_y = (p2.y - p1.y) * (p2.y - p1.y);
		return Math.sqrt(kw_x + kw_y);
	}*/

}