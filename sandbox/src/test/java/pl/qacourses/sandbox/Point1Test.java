package pl.qacourses.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Point1Test {
    @Test
    public void testDistance1() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(5,0);
        Assert.assertEquals(p1.distance(p2),4.0);
    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,3);
        Assert.assertEquals(p1.distance(p2),3.0);
    }
    @Test
    public void testDistance3() {
        Point p1 = new Point(2,2);
        Point p2 = new Point(5,-2);
        Assert.assertEquals(p1.distance(p2),5.0);
    }
}
