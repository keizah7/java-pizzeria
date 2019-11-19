package lt.bit.pizzeria;

import lt.bit.pizzeria.items.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class PizzeriaMain {
    private static final String PATH = "/home/keizah/java/java-pizzeria/txt/menu.txt";
    private static boolean space = true;
    private static boolean ordering = true;
    private static Map<String, List> menuItems = new TreeMap<>(Collections.reverseOrder());
    private static List<MenuItem> items = new LinkedList<>();
    private static List<MenuItem> allItems = new LinkedList<>();
    private static Map<Integer, Integer> ordered = new TreeMap<>();
    private static String category = "";
    private static int i = 0;
    private static double price = 0;
    private static Scanner c = new Scanner(System.in);

    public static void main(String[] args) {
        createItemsFromMenu();
        welcome();
    }

    private static void welcome() {
        System.out.println("Sveiki atvykę į Mūsų restoraną, prašome, sėstis čia.\nAr atnešti Jums meniu? (t / n)");

        boolean welcome = true;
        do {
            String answer = c.nextLine();

            if (answer.equals("t")) {
                printMenu();

                review();
                break;
            } else if (answer.equals("n")) break;
            else System.out.println("t(taip) arba n(ne)");
        } while (welcome);

        System.out.println("\n\tViso gero!");
    }

    private static void review() {
        System.out.println("Peržiūrėkite meniu. (i(išeiti) / u(užsisakyti)");

        boolean review = true;
        do {
            String answer = c.next();
            if (answer.equals("u")) {
                ordering();
                break;
            } else if (answer.equals("i")) break;
            else System.out.println("(i(išeiti) / u(užsisakyti)");
        } while (review);
    }

    private static void ordering() {
        String text = "Rinkitės: (int / int ...int) / (.(išsirinkau) / i(išeiti)) / m(pažiūrėti į meniu) ";
        System.out.print(text);
        int lastInt = 0;
        ordered = new TreeMap<>();

        do {
            String answer = c.next();

            if (answer.equals("i")) break;
            else if (answer.equals("m")) {
                printMenu();

                System.out.print(text);
            } else if (answer.equals(".")) {
                finishOrdering(false);
                break;
            } else if (answer.matches("\\d+")) {
                int answerInt = Integer.parseInt(answer);
                if (answerInt <= allItems.size() & answerInt > 0) {
                    int uzs = answerInt - 1;

                    Integer count = ordered.get(uzs);

                    System.out.print(Color.BBLACK);
                    if (count == null) {
                        System.out.print(allItems.get(uzs).getType() + ", ");
                        if(ordered.size() % 5 == 0) System.out.println();
                        ordered.put(uzs, 1);
                    } else {
                        ordered.put(uzs, ++count);
                        if (lastInt == uzs) System.out.print("->" + count + "vnt. ");
                        else System.out.print(allItems.get(uzs).getType() + " (" + count + "vnt.), ");
                    }
                    System.out.print(Color.RESET);
                } else System.out.print("\n" + Color.RED + answerInt + " - produkto meniu sąraše nėra!" + Color.BBLACK + " ");
                lastInt = answerInt - 1;
            } else {
                System.out.println("int(enter) / (.(išsirinkau) / i(išeiti)) / m(pažiūrėti į meniu)");
                ordering = true;
            }
        } while (ordering);
    }

    private static void finishOrdering(boolean finished) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.print(
                "\n\n"
                + (finished ? Color.GREEN_BG : Color.YELLOW_BG)
                + "\n\t"
                + (finished ? "Sąskaita faktūra (" + ldt.format(DateTimeFormatter.ofPattern("YYYY.MM.dd HH:mm")) + ")" : "Jūsų pasirinkti produktai:")
                + "\n\n" + Color.RESET
        );
        price = 0;

        System.out.println();

        ordered.forEach((integer, count) ->
        {
            MenuItem item = allItems.get(integer);
            price += (double) (count * item.getPrice());
            final String dots = ".".repeat(70 - item.getType().length() - (count > 1 ? count.toString().length() + 7 : 0));
            System.out.printf("\t%s%s %s %5.2f €%n", item.getType(), count > 1 ? " (" + count + "vnt.)" : "", dots, count * item.getPrice());
        });
        if (finished) {
            System.out.printf("\n\t%sViso: %.2f €%s%n", Color.UBLACK, price, Color.RESET);
            int randomMinutes = (int) (Math.random() * 10 + 1);
            int cookingTime = randomMinutes * ordered.size();
            int hh = cookingTime / 60; //since both are ints, you get an int
            int mm = cookingTime - hh * 60;
            System.out.print("\n\tUžsakymas bus paruoštas už ");
            System.out.printf("%s %s", (hh > 0 ? hh+"h " : ""), (mm > 0 ? mm+"min." : ""));
            System.out.println(" (" + ldt.plusMinutes(cookingTime).format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
            System.out.println("\n\tSKANAUS :)");
        }

        if (!finished) finalDecision();
    }

    private static void finalDecision() {
        String text = Color.UBLACK + "\nRinkitės:" + Color.RESET + " k(" + Color.GREEN + "PATVIRTINU" + Color.RESET + ") / u(" + Color.BLUE + "Užsisakyti iš naujo" + Color.RESET + ") / i(" + Color.RED + "išeiti" + Color.RESET + ") ";
        System.out.print(text);

        boolean decision = true;
        do {
            String answer = c.next();
            if (answer.equals("k")) {
                finishOrdering(true);
                break;
            } else if (answer.equals("i")) break;
            else if (answer.equals("u")) {
                System.out.println("\n\n\tRinkis iš naujo.........");
                ordering();
                break;
            } else System.out.println(text);
        } while (decision);
    }

    private static void printMenu() {
        printMenuHeader();
        menuItems.forEach(PizzeriaMain::printItems);
        printMenuFooter();
    }

    private static void printMenuFooter() {
        System.out.println("\n" + Color.RED_BG + "" + Color.BBLACK + "\n\t" + "\u00a9 Artūras M.\n");
        System.out.println(Color.RESET);
    }

    private static void printMenuHeader() {
        category = "";
        i = 0;
        System.out.print(Color.RED_BG + "" + Color.BBLACK + "\n\tSaulėtekio Pizzeria & Wok\n\t" + Color.WHITE + "Restorano meniu:\n\n" + Color.RESET);
    }

    private static void printItem(String key, MenuItem item) {
        allItems.add(item);

        printItemTitle(key);
        printItemName(item);
        printDots(item);
        printItemInfo(item);
    }

    private static void printItemInfo(MenuItem item) {
        if (item.getCapacity() != null)
            if (!item.getCapacity().endsWith(")")) System.out.print(item.getCapacity() + " / ");
        System.out.println(String.format("%4.2f €", item.getPrice()));
        if (item.getInfo() != null) if (item.getInfo().endsWith(")")) System.out.println("\t\t" + item.getInfo());
    }

    private static void printItemName(MenuItem item) {
        System.out.println(Color.BLACK_BG);
        System.out.print("\t" + Color.RED + (++i < 10 ? " " + i : i) + ". " + Color.WHITE);
        System.out.print(item.getType() + " ");
    }

    private static void printItemTitle(String key) {
        if (!category.equals(key)) {
            if (i != 0) System.out.println();
            category = key;
            System.out.print(Color.GREEN_BG + "\t" + Color.BLACK);
            System.out.println(category);
        }
    }

    private static void printDots(MenuItem s) {
        final int dots = 200;

        System.out.print(".".repeat(dots - s.getType().length() - ((s.getCapacity() != null) ? (!s.getCapacity().endsWith(")") ? s.getCapacity().length() + 3 : 0) : 0)) + " ");
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

    private static void printItems(String key, List value) {
        for (Object o : value) printItem(key, (MenuItem) o);
    }
}
