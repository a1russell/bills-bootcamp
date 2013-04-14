package problem2;

import org.javatuples.Triplet;
import problem2.internal.Vector;

public class Rectangle {
    private Triplet<Point, Point, Point> corners;

    public Rectangle(Point corner1, Point corner2, Point corner3) {
        this.corners = Triplet.with(corner1, corner2, corner3);
    }

    public boolean contains(Point point) {
        Vector ab = new Vector(corners.getValue0(), corners.getValue1());
        Vector ap = new Vector(corners.getValue0(), point);
        Vector bc = new Vector(corners.getValue1(), corners.getValue2());
        Vector bp = new Vector(corners.getValue1(), point);

        return 0 <= ab.dotProduct(ap) &&
               ab.dotProduct(ap) <= ab.dotProduct(ab) &&
               0 <= bc.dotProduct(bp) &&
               bc.dotProduct(bp) <= bc.dotProduct(bc);
    }
}
