package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Food;

public class Pizza extends Food {
    private static final String NAME = "Pica";

    public Pizza(String type, double price) {
        super(NAME, type, price);
    }
}
