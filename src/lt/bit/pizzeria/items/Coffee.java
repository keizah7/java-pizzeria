package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Coffee extends Drink {
    private static final String NAME = "Kava";

    public Coffee(String type, double price) {
        super(NAME, type, price);
    }
}
