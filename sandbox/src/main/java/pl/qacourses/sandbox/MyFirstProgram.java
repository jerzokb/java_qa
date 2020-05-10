package pl.qacourses.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {

		hello("world");
		hello("Beata");

		double len = 5;
		System.out.println("Powierzchnia kwadratu o boku " + len + " = " + area(len));

		double a = 4;
		double b = 5;
		System.out.println("Powierzchnia prostokata o bokach " + a + " i " + b + " = " + area(len));
	}

	public static void hello(String somebody) {
		System.out.println("Hello " + somebody);
	}

	public static double area(double l) {
		return l*l;
	}

	public static double area(double a, double b) {
		return a*b;
	}

}