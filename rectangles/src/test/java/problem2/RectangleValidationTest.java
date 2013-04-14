package problem2;

import org.junit.Test;

public class RectangleValidationTest {
    @Test(expected=IllegalArgumentException.class)
    public void shouldNotConstructGivenNonPerpendicularSides() {
        Point topLeft = new Point(0, 2);
        Point topRight = new Point(2, 2);
        Point bottomRight = new Point(1, 1);

        new Rectangle(topLeft, topRight, bottomRight);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotConstructWithNoWidth() {
        Point topLeft = new Point(0, 1);
        Point topRight = topLeft;
        Point bottomRight = new Point(0, 0);

        new Rectangle(topLeft, topRight, bottomRight);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotConstructWithNoHeight() {
        Point topLeft = new Point(0, 0);
        Point topRight = new Point(1, 0);
        Point bottomRight = topRight;

        new Rectangle(topLeft, topRight, bottomRight);
    }
}