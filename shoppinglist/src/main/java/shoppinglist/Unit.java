package shoppinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Unit {
    TSP("tsp"),
    TBSP("tbsp"),
    FL_OZ("fl oz"),
    CUP("cup");

    @Getter
    private final String text;
}
