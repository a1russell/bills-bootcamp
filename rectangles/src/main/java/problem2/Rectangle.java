package problem2;

import org.javatuples.Triplet;

public class Rectangle {
    private Triplet<Point, Point, Point> corners;

    public Rectangle(Point corner1, Point corner2, Point corner3) {
        this.corners = Triplet.with(corner1, corner2, corner3);
    }

    public boolean contains(Point point) {
        return true;
    }
}
