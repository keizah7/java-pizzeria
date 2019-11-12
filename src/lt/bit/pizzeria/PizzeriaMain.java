package lt.bit.pizzeria;

import lt.bit.pizzeria.items.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PizzeriaMain {
    private static List<MenuItem> menuItems;

    public static void main(String[] args) {
        addMenuItemsToList();
    }

    private static void addMenuItemsToList() {
        menuItems = new LinkedList<>(Arrays.asList(
            new Wok("Ryžių makaronai su vištiena", 4.9),
            new Wok("Kiaušininiai makaronai su kiauliena|", 4.9),
            new Wok("Kiaušininiai makaronai su krevetėmis", 4.9),
            new Wok("Ryžiai su krevetėmis", 4.9),
            new Pizza("Egzotiška", 7.9),
            new Pizza("Gardžioji", 5.8),
            new Pizza("Firminė su vištiena", 7.9),
            new Pizza("Aitriųjų paprikų", 8),
            new Pizza("Akapulko", 9),
            new Pizza("Aštrioji", 8),
            new Bear("Volfas Engelman Rinktinis", 2.5, 0.57),
            new Bear("Volfas Engelman Balta Pinta", 2.5, 0.57),
            new Bear("Volfas Engelman APA", 2.5, 0.57),
            new Bear("Volfas Engelman Kriek", 2.5, 0.57),
            new Bear("Carlsberg", 2.5, 500),
            new Bear("Saporo Japoniškas", 2.5, 330),
            new Bear("Švyturio baltas", 2.5, 500),
            new Bear("Švyturio nealkoholinis", 2.5, 500),
            new Beverage("Stalo vanduo", 0.3, 250),
            new Beverage("Mineralinis vanduo „Neptūnas“", 1.5, 300),
            new Beverage("Gira", 1.5, 500),
            new Beverage("Sultys", 1.2, 250),
            new Tea("Žalioji", 1.5),
            new Tea("Juodoji", 1.5),
            new Tea("Šaltalankių", 2),
            new Coffee("Esspreso", 1.2),
            new Coffee("Juoda", 1.2),
            new Coffee("Juoda su pienu", 1.5),
            new Coffee("Latte", 1.8),
            new Coffee("Cappuccino", 1.8)
        ));
    }
}
