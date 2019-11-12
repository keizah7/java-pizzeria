package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Bear extends Drink {
    private static final String NAME = "Alus";
    private double capacity;

    public Bear(String type, double price, double capacity) {
        super(NAME, type, price);
        this.capacity = capacity;
    }
}
