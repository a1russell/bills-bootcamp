import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RectangleTest {

    private Rectangle rectangle;

    @Before
    public void setup(){
        Point topLeft = new Point(0, 2);
        Point topRight = new Point(2, 2);
        Point bottomRight = new Point(2, 0);

        rectangle = new Rectangle(topLeft, topRight, bottomRight);
    }

    @Test
    public void shouldDeterminePointIsInside() {
        Point point = new Point(1, 1);
        assertTrue(rectangle.contains(point));
    }
}
