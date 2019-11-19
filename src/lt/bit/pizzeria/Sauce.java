package lt.bit.pizzeria;

public enum Sauce {
    MILD("Švelnus"),
    MIX("Mixas"),
    SPICY("Aštrus"),
    BBQ("Barbekiu");

    private String description;

    Sauce(String description) {
        this.description = description;
    }
}
