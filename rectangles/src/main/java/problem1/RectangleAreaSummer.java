package problem1;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Collection;

public class RectangleAreaSummer {
    private Multiset<Rectangle> rectangles;

    public RectangleAreaSummer(Collection<Rectangle> rectangles) {
        this.rectangles = HashMultiset.create(rectangles);
    }

    public int sumAreaOfRectangles() {
        int areaSum = 0;
        for (Rectangle rectangle : rectangles) {
            areaSum += rectangle.getArea();
        }
        return areaSum;
    }
}
