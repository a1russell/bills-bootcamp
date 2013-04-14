package problem1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @Test
    public void shouldCalculateArea() {
        Rectangle rectangle = new Rectangle(3, 2);
        assertEquals(6, rectangle.getArea());
    }
}
