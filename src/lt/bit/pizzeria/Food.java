package lt.bit.pizzeria;

public abstract class Food extends MenuItem {
    public Food(String name, String type, double price) {
        super(name, type, price);
    }
    public Food(String name, String type, double price, String info) {
        super(name, type, price, info);
    }
}
