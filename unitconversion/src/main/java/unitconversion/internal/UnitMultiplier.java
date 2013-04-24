package unitconversion.internal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class UnitMultiplier {
    private final int id;
    @Getter private final double multiplier;
}
