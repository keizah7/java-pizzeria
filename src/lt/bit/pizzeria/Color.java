package lt.bit.pizzeria;

public enum Color {
    RESET("\u001B[0m"),
    BLACK_BG("\u001B[40m"),
    RED_BG("\u001B[41m"),
    GREEN_BG("\u001B[42m"),
    BLACK("\u001B[30m"),
    BBLACK("\u001B[1;30m"),
    RED("\u001B[31m"),
    WHITE("\u001B[37m");

    String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}