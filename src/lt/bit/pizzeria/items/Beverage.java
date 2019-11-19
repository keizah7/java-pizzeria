package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Beverage extends Drink {
    private static final String NAME = "Gaivusis gėrimas";
    private String capacity;
    private String info;

    public Beverage(String type, double price, String capacity, String info) {
        super(NAME, type, price, info, capacity);
        this.capacity = capacity;
        this.info = info;
    }

    @Override
    public String toString() {
        return "\n"
                + getType()
                + " - "
                + capacity
                + " / "
                + getPrice() + " €\n\t"
                + info;
    }
}
