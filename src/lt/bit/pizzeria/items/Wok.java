package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Food;

public class Wok extends Food {
    private static final String NAME = "Wok";

    public Wok(String type, double price) {
        super(NAME, type, price);
    }
}
