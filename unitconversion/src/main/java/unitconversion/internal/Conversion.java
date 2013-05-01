package unitconversion.internal;

import com.google.common.base.Function;

public class Conversion {
    private final int id;
    private final Function<Double, Double> conversion;

    public Conversion(int id, Function<Double, Double> conversion) {
        if (conversion == null) throw new NullPointerException("conversion");
        this.id = id;
        this.conversion = conversion;
    }

    public Function<Double, Double> getConversion() {
        return conversion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversion that = (Conversion) o;

        return id == that.id && conversion.equals(that.conversion);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + conversion.hashCode();
        return result;
    }
}
