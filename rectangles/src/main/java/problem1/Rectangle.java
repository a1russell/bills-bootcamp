package problem1;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Rectangle {
    @NonNull private int width;
    @NonNull private int height;

    public int getArea() {
        return width * height;
    }
}
