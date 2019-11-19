package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Food;

public class Pizza extends Food {
    private static final String NAME = "Pica";
    private String info;

    public Pizza(String type, double price, String info) {
        super(NAME, type, price, info);
        this.info = info;
    }

    @Override
    public String toString() {
        return "\n"
                + getType()
                + " - "
                + getPrice() + " €\n\t"
                + info
                ;
    }
}
