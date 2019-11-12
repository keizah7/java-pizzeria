package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Tea extends Drink {
    private static final String NAME = "Arbata";

    public Tea(String type, double price) {
        super(NAME, type, price);
    }
}
