package lt.bit.pizzeria;

public abstract class Drink extends MenuItem {
    public Drink(String name, String type, double price) {
        super(name, type, price);
    }

    public Drink(String name, String type, double price, String info, String capacity) {
        super(name, type, price, info, capacity);
    }
}
