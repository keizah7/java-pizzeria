package lt.bit.pizzeria;

abstract class MenuItem implements Comparable<MenuItem> {
    private String name;
    private String type;
    private double price;
    private String info;
    private String capacity;

    public MenuItem(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public MenuItem(String name, String type, double price, String info) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.info = info;
    }

    public MenuItem(String name, String type, double price, String info, String capacity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.info = info;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public String getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "\n"
                + type
                + " - " + price + " €";
    }

    @Override
    public int compareTo(MenuItem menuItem) {
        return Double.compare(this.getPrice(), menuItem.getPrice());
//        if (this.getPrice() < menuItem.getPrice()) return -1;
//        if (this.getPrice() > menuItem.getPrice()) return 1;
//        return 0;
    }
}
