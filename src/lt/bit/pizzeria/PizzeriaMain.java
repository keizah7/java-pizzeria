package lt.bit.pizzeria;

import lt.bit.pizzeria.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PizzeriaMain {
    private static final String PATH = "/home/keizah/java/java-pizzeria/txt/";
    private static List<MenuItem> menuItems;
    private static boolean space = true, welcome = true, review = true;
    private static Scanner c;

    public static void main(String[] args) {
        addMenuItemsToList();

        welcome();
    }

    private static void welcome() {
        System.out.println("Sveiki atvykę į Mūsų restoraną, prašome, sėstis čia.\nAr atnešti Jums meniu? (t / n)");
        c = new Scanner(System.in);

        do {
            String answer = c.nextLine();

            if(answer.equals("t")){
                printMenu();
                review();
                break;
            } else if(answer.equals("n")) break;
            else System.out.println("t(taip) arba n(ne)");
        } while (welcome);

        System.out.println("Viso gero!");
    }

    private static void review() {
        System.out.println("Peržiūrėkite meniu. (i(išeiti) / u(užsisakyti)");

        do {
            String answer = c.next();
            if(answer.equals("u")) {
                System.out.println("uzsisakyti");
            } else if(answer.equals("i")) break;
            else System.out.println("(i(išeiti) / u(užsisakyti)");
        } while (review);
    }

    private static void printMenu(){
        System.out.print(Color.RED_BG +""+ Color.BBLACK+"\n\tSaulėtekio Pizzeria & Wok\n\t"+Color.WHITE+"Restorano meniu:\n\n"+Color.RESET);

        try {
            int i               = 0;
            File myObj          = new File(PATH + "menu.txt");
            Scanner myReader    = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                if(myReader.hasNextLine()) {
                    String line     = myReader.nextLine();
                    String[] data   = line.split(Pattern.quote("|"));

                    if(data.length == 1) {
                        if(line.charAt(0) == '*') {
                            if(i > 1 && space) System.out.println();

                            System.out.print(Color.YELLOW_BG +"\t"+Color.UBLACK);
                            System.out.print(line.substring(1)+":");

                            space = false;
                        } else if (line.charAt(0) == '-') {
                            System.out.print(Color.RED_BG +"\t"+Color.BLACK);
                            System.out.print(line.substring(1)+":");
                        } else {
                            if(i > 1 && space) System.out.println();

                            System.out.print(Color.GREEN_BG +"\t"+Color.BLACK);
                            System.out.print(line+":");

                            space = true;
                        }
                    } else if (data.length > 1){
                        int spaces = 150;
                        i++;

                        System.out.println(Color.BLACK_BG+""+Color.RED);
                        System.out.print("\t"+ (i < 10 ? " " : "") + i + ". " +Color.WHITE + data[1] + ".".repeat(
                                (data.length > 2) ? spaces - line.length() - 2 + (data.length > 3 ? 2 : 0) : spaces - line.length()
                        ) + " " + (data.length > 2 && !data[2].equals("+") ? data[2]+" / " : "") + data[0] +" €");

                        if(data.length == 3) {
                            String details = myReader.nextLine();
                            System.out.print("\n\t"+" ".repeat(4) + details);
                        }
                    }

                    System.out.println();
                    if(data.length == 1) System.out.print(Color.RESET);
                }
            }
            myReader.close();

            System.out.println("\n"+Color.RED_BG +""+ Color.BBLACK+"\n\t"+"\u00a9 Artūras M.\n");
            System.out.print(Color.RESET);
        } catch (FileNotFoundException e) {
            System.out.println("\tDuomenys nerasti");
        }
        System.out.println(Color.RESET);
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
