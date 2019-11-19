package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Drink;

public class Beer extends Drink {
    private static final String NAME = "Alus";
    private String info;
    private String capacity;

    public Beer(String type, double price, String capacity, String info) {
        super(NAME, type, price, info, capacity);
        this.capacity = capacity;
        this.info = info;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
