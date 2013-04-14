package problem2;

import org.javatuples.Triplet;
import problem2.internal.Vector;

public class Rectangle {
    private Triplet<Point, Point, Point> corners;

    public Rectangle(Point corner1, Point corner2, Point corner3) {
        this.corners = Triplet.with(corner1, corner2, corner3);
        validate();
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

    private void validate() {
        Vector ab = new Vector(corners.getValue0(), corners.getValue1());
        Vector bc = new Vector(corners.getValue1(), corners.getValue2());
        validateWidthAndHeight(ab, bc);
        validatePerpendicularSides(ab, bc);
    }

    private void validateWidthAndHeight(Vector ab, Vector bc) {
        if (ab.isNullVector() || bc.isNullVector()) {
            throw new IllegalArgumentException("Must have nonzero width and height.");
        }
    }

    private void validatePerpendicularSides(Vector ab, Vector bc) {
        if (ab.dotProduct(bc) != 0) {
            throw new IllegalArgumentException("The side formed by corner1 and corner2" +
                                               "must form a right angle with the side " +
                                               "formed by corner2 and corner3.");
        }
    }
}
