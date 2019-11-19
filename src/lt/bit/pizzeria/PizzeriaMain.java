package lt.bit.pizzeria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PizzeriaMain {
    private static boolean ordering = true;
    private static Map<String, List> menuItems = new TreeMap<>(Collections.reverseOrder());
    private static List<MenuItem> allItems = new LinkedList<>();
    private static Map<MenuItem, Integer> ordered = new TreeMap<>();
    private static int i = 0;
    private static double price = 0;
    private static Scanner c = new Scanner(System.in);
    private static String category = "";

    public static void main(String[] args) {
        welcome();
    }

    private static void welcome() {
        Message.println("welcome");
        boolean welcome = true;

        label:
        do {
            String answer = c.nextLine();

            switch (answer) {
                case "taip":
                    menuItems = new CreateMenuItems().get();
                    printMenu();
                    review();
                    break label;
                case "ne":
                    welcome = false;
                    break;
                default:
                    Message.println("welcome2");
                    break;
            }
        } while (welcome);

        Message.print("bye");
    }

    private static void review() {
        Message.println("review");

        boolean review = true;
        label:
        do {
            String answer = c.next();
            switch (answer) {
                case "uzsakyti":
                    ordering();
                    break label;
                case "iseiti":
                    review = false;
                    break;
                default:
                    Message.println("review2");
                    break;
            }
        } while (review);
    }

    private static void ordering() {
        Message.println("choose");
        MenuItem lastItem = variablesInit();

        do {
            String answer = c.next();

            if (answer.equals("iseiti")) break;
            else if (answer.equals("meniu")) {
                printMenu();

                Message.println("choose");
            } else if (answer.equals("issirinkau")) {
                finishOrdering(false);
                break;
            } else if (answer.matches("\\d+")) {
                int answerInt = Integer.parseInt(answer);

                if (answerInt <= allItems.size() & answerInt > 0) {
                    int booked = answerInt - 1;

                    MenuItem item = allItems.get(booked);

                    System.out.print(Color.BBLACK);
                    if ((boolean) ordered.containsKey(item)) {
                        Integer count = ordered.get(item);

                        ordered.put(item, count);

                        if (lastItem == item) System.out.print("->" + count + "vnt. ");
                        else if (count > 1) System.out.print(item.getType() + " (" + count + "vnt.), ");
                    } else {
                        System.out.print(item.getType() + ", ");
                        ordered.put(item, 1);
                        if (ordered.size() % 4 == 0) System.out.println();
                    }
                    System.out.print(Color.RESET);
                    lastItem = item;
                } else
                    System.out.print("\n" + Color.RED + answerInt + " - produkto meniu sąraše nėra!" + Color.BBLACK + " ");
            } else Message.println("choose2");
        } while (ordering);
    }

    private static MenuItem variablesInit() {
        MenuItem lastItem = null;
        ordered = new TreeMap<>();
        category = "";
        i = 0;
        return lastItem;
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

        ordered.forEach((item, count) ->
        {
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
            System.out.printf("%s %s", (hh > 0 ? hh + "h " : ""), (mm > 0 ? mm + "min." : ""));
            System.out.println(" (" + ldt.plusMinutes(cookingTime).format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
            System.out.println("\n\tSKANAUS :)");
        }

        if (!finished) finalDecision();
    }

    private static void finalDecision() {
        Message.println("finalChoose");
        boolean decision = true;

        label:
        do {
            String answer = c.next();
            switch (answer) {
                case "testi":
                    finishOrdering(true);
                    break label;
                case "iseiti":
                    decision = false;
                    break;
                case "persigalvojau":
                    Message.println("finalChoose");
                    System.out.println("\n\n\tRinkitės iš naujo..");
                    ordering();
                    break label;
                default:
                    Message.println("finalChoose");
                    break;
            }
        } while (decision);
    }

    private static void printMenu() {
        Message.print("header");
        menuItems.forEach(PizzeriaMain::printItems);
        Message.print("footer");
    }

    private static void printItem(String key, MenuItem item) {
        allItems.add(item);

        printItemTitle(key);
        printItemName(item);
        printDots(item);
        printItemInfo(item);
    }

    private static void printItemTitle(String key) {
        if (!category.equals(key)) {
            if (i != 0) System.out.println();
            category = key;
            System.out.print(Color.GREEN_BG + "\t" + Color.BLACK);
            System.out.println(category);
        }
    }

    private static void printItemInfo(MenuItem item) {
        if (item.getCapacity() != null)
            if (!item.getCapacity().endsWith(")")) System.out.print(item.getCapacity() + " / ");
        System.out.println(String.format("%4.2f €", item.getPrice()));
        if (item.getInfo() != null) if (item.getInfo().endsWith(")")) System.out.println("\t\t" + item.getInfo());
    }

    private static void printItemName(MenuItem item) {
        System.out.print(Color.BLACK_BG+"\n\t" + Color.RED + (++i < 10 ? " " + i : i) + ". " + Color.WHITE);
        System.out.print(item.getType() + " ");
    }

    private static void printDots(MenuItem s) {
        final int dots = 200;

        System.out.print(".".repeat(dots - s.getType().length() - ((s.getCapacity() != null) ? (!s.getCapacity().endsWith(")") ? s.getCapacity().length() + 3 : 0) : 0)) + " ");
    }

    private static void printItems(String key, List value) {
        for (Object o : value) printItem(key, (MenuItem) o);
    }
}
