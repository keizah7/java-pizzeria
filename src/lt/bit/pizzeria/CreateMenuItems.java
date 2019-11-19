package lt.bit.pizzeria;

import lt.bit.pizzeria.items.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CreateMenuItems {
    private static final String PATH = "/home/keizah/java/java-pizzeria/txt/menu.txt";
    private static List<MenuItem> items = new LinkedList<>();
    private static String category = "";
    private static Map<String, List> menuItems = new TreeMap<>(Collections.reverseOrder());

    public CreateMenuItems() {
        createItemsFromMenu();
    }

    public Map get() {
        return menuItems;
    }

    private static void createItemsFromMenu() {
        try {
            Files.readAllLines(Paths.get(PATH))
                    .stream()
                    .map(s -> s.split("\n"))
                    .forEach(
                            strings -> {
                                String[] arr = strings[0].split(Pattern.quote("|"));
                                if (!category.equals(arr[0])) {
                                    category = arr[0];
                                    items = new LinkedList<>();
                                }
                                menuItems.put(category, addMenuItemsToList(arr));
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<MenuItem> addMenuItemsToList(String[] arr) {
        String category = arr[0];
        items.add(createMenuItemByCategoryName(arr));
        Collections.sort(menuItems.getOrDefault(category, new LinkedList<>()));

        return items;
    }

    private static MenuItem createMenuItemByCategoryName(String[] arr) {
        String item = arr[0];
        double p = Double.parseDouble(arr[1]);
        String t = arr[2];
        String info = arr.length > 3 ? arr[3] : "";
        String capacity = arr.length > 4 ? arr[4] : "";

        return returnMenuItem(item, p, t, info, capacity);
    }

    private static MenuItem returnMenuItem(String item, double p, String t, String info, String capacity) {
        switch (item) {
            case "Wok":
                return new Wok(t, p, info);
            case "Pica":
                return new Pizza(t, p, info);
            case "Alus":
                return new Beer(t, p, info, capacity);
            case "Gaivusis gėrimas":
                return new Beverage(t, p, info, capacity);
            case "Arbata":
                return new Tea(t, p);
            case "Kava":
                return new Coffee(t, p);
            default:
                return null;
        }
    }
}
