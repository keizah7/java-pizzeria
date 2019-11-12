package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Beverage extends Drink {
    private static final String NAME = "Gaivusis gėrimas";
    private double capacity;

    public Beverage(String type, double price, double capacity) {
        super(NAME, type, price);
        this.capacity = capacity;
    }
}
