package lt.bit.pizzeria;

abstract class MenuItem {
    private String name;
    private String type;
    private double price;

    public MenuItem(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
