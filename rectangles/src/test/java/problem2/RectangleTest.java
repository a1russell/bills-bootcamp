package problem2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RectangleTest {

    private Rectangle rectangle;

    @Before
    public void setup() {
        Point topLeft = new Point(0, 2);
        Point topRight = new Point(2, 2);
        Point bottomRight = new Point(2, 0);

        rectangle = new Rectangle(topLeft, topRight, bottomRight);
    }

    @Test
    public void shouldContainPoint() {
        Point point = new Point(1, 1);
        assertTrue(rectangle.contains(point));
    }

    @Test
    public void shouldNotContainPointToLeft() {
        Point point = new Point(-1, 1);
        assertFalse(rectangle.contains(point));
    }

    @Test
    public void shouldNotContainPointAbove() {
        Point point = new Point(1, 3);
        assertFalse(rectangle.contains(point));
    }

    @Test
    public void shouldNotContainPointToRight() {
        Point point = new Point(3, 1);
        assertFalse(rectangle.contains(point));
    }

    @Test
    public void shouldNotContainPointBelow() {
        Point point = new Point(1, -1);
        assertFalse(rectangle.contains(point));
    }

    @Test
    public void shouldContainPointOnLeftLine() {
        Point point = new Point(0, 1);
        assertTrue(rectangle.contains(point));
    }

    @Test
    public void shouldContainPointOnTopLine() {
        Point point = new Point(1, 2);
        assertTrue(rectangle.contains(point));
    }

    @Test
    public void shouldContainPointOnRightLine() {
        Point point = new Point(2, 1);
        assertTrue(rectangle.contains(point));
    }

    @Test
    public void shouldContainPointOnBottomLine() {
        Point point = new Point(1, 0);
        assertTrue(rectangle.contains(point));
    }
}
