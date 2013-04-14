package problem1;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class RectangleAreaSummerTest {
    @Test
    public void shouldSumTwoRectangles() {
        Rectangle rectangle1 = new Rectangle(2, 3);
        Rectangle rectangle2 = new Rectangle(3, 4);
        RectangleAreaSummer rectangleAreaSummer = new RectangleAreaSummer(newArrayList(rectangle1, rectangle2));
        assertEquals(18, rectangleAreaSummer.sumAreaOfRectangles());
    }
}
