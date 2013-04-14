package problem2.internal;

import problem2.Point;

public class Vector {
    private Point coordinate;

    public Vector(Point a, Point b) {
        this.coordinate = new Point(b.getX() - a.getX(), b.getY() - a.getY());
    }

    public boolean isNullVector() {
        return coordinate.getX() == 0 && coordinate.getY() == 0;
    }

    public double dotProduct(Vector that) {
        return this.coordinate.getX() * that.coordinate.getX() +
               this.coordinate.getY() * that.coordinate.getY();
    }
}
