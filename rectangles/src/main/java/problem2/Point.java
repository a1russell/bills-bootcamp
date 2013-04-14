package problem2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
@AllArgsConstructor
public class Point {
    @Getter @NonNull protected double x;
    @Getter @NonNull protected double y;
}
