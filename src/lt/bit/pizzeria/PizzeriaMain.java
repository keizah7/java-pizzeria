package lt.bit.pizzeria;

import lt.bit.pizzeria.items.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class PizzeriaMain {
    private static final String PATH = "/home/keizah/java/java-pizzeria/txt/menu.txt";
    private static boolean welcome = true, space = true, ordering = true, review = true;
    private static Map<String, List> menuItems = new TreeMap<>(Collections.reverseOrder());
    private static List<MenuItem> items = new LinkedList<>();
    private static String category = "";
    private static int i = 0;
    private static Scanner c = new Scanner(System.in);

    public static void main(String[] args) {
        createItemsFromMenu();

        welcome();
    }

    private static void welcome() {
        System.out.println("Sveiki atvykę į Mūsų restoraną, prašome, sėstis čia.\nAr atnešti Jums meniu? (t / n)");

        do {
            String answer = c.nextLine();

            if (answer.equals("t")) {
                printMenu();
                review();
                break;
            } else if (answer.equals("n")) break;
            else System.out.println("t(taip) arba n(ne)");
        } while (welcome);

        System.out.println("Viso gero!");
    }

    private static void review() {
        System.out.println("Peržiūrėkite meniu. (i(išeiti) / u(užsisakyti)");

        do {
            String answer = c.next();
            if(answer.equals("u")) {
                ordering();
                break;
            } else if(answer.equals("i")) break;
            else System.out.println("(i(išeiti) / u(užsisakyti)");
        } while (review);
    }

    private static void ordering(){
        String text = "Rinkitės: int(enter) / (.(išsirinkau) / i(išeiti)) / m(pažiūrėti į meniu)";
        System.out.print(text);

        do {
            String answer = c.next();

            if(answer.equals("i")) break;
            else if(answer.equals("m")) {
                printMenu();

                System.out.print(text);
            } else if(answer.equals(".")) {
//                System.out.print("Galutinė suma yra:");
//                System.out.println(orderedItems.toString());
                break;
            } else if(answer.matches("\\d+")) {
                int answerInt = Integer.parseInt(answer);
                if(answerInt <= menuItems.size() & answerInt > 0) {
                    int uzs = Integer.parseInt(answer);
//                    orderedItems.add(menuItems.get(uzs-1));
                } else System.out.println("Tokio produkto meniu sąraše nėra");
            } else {
                System.out.println("int(enter) / (.(išsirinkau) / i(išeiti)) / m(pažiūrėti į meniu)");
                System.out.println(answer);
                ordering = true;
            }
        } while (ordering);
    }

    private static void printMenu() {
        System.out.print(Color.RED_BG + "" + Color.BBLACK + "\n\tSaulėtekio Pizzeria & Wok\n\t" + Color.WHITE + "Restorano meniu:\n\n" + Color.RESET);
        category = "";

        menuItems.forEach((key, value) -> {
            for (Object o : value) {
                final int dots = 200;
                MenuItem s = (MenuItem) o;

                if (!category.equals(key)) {
                    if (i != 0) System.out.println();
                    category = key;
                    System.out.print(Color.GREEN_BG + "\t" + Color.BLACK);
                    System.out.println(category);
                }

                System.out.println(Color.BLACK_BG);
                System.out.print("\t" + Color.RED + (++i < 10 ? " " + i : i) + ". " + Color.WHITE);
                System.out.print(s.getType() + " ");

                System.out.print(".".repeat(
                        dots - s.getType().length() - ((s.getCapacity() != null) ? (!s.getCapacity().endsWith(")") ? s.getCapacity().length() + 3 : 0) : 0)
                ) + " ");

                if (s.getCapacity() != null)
                    if (!s.getCapacity().endsWith(")")) System.out.print(s.getCapacity() + " / ");

                System.out.println(String.format("%s €", s.getPrice()));
                if (s.getInfo() != null) if (s.getInfo().endsWith(")")) System.out.println("\t\t" + s.getInfo());
            }
        });
        System.out.println("\n" + Color.RED_BG + "" + Color.BBLACK + "\n\t" + "\u00a9 Artūras M.\n");
        System.out.println(Color.RESET);
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
