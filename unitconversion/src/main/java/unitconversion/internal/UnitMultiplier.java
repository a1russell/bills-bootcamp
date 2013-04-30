package unitconversion.internal;

public class UnitMultiplier {
    private final int id;
    private final double multiplier;

    public UnitMultiplier(int id, double multiplier) {
        this.id = id;
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitMultiplier that = (UnitMultiplier) o;

        return id == that.id && Double.compare(that.multiplier, multiplier) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(multiplier);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
