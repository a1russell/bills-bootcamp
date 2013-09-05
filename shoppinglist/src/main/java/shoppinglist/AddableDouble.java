package shoppinglist;

class AddableDouble implements Addable<AddableDouble> {
    private double value;

    public AddableDouble(double value) {
        this.value = value;
    }

    @Override
    public void add(AddableDouble that) {
        this.value += that.getValue();
    }

    public double getValue() {
        return value;
    }
}
