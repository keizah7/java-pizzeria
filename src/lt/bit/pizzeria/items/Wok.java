package lt.bit.pizzeria.items;

import lt.bit.pizzeria.Food;

public class Wok extends Food {
    private static final String NAME = "Wok";
    private String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public Wok(String type, double price, String info) {
        super(NAME, type, price, info);
        setInfo(info);
    }

    @Override
    public String toString() {
        return "\n"
                + getType()
                + " - "
                + getPrice() +" €\n\t"
                + info
                ;
    }
}
