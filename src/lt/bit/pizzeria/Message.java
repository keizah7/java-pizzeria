package lt.bit.pizzeria;

import java.util.Map;
import java.util.TreeMap;

class Message {
    private static Map<String, String> messages;
    private static String pleaseChoose = "Prašome, pasirinkti: ";

    static {
        messages = new TreeMap<>() {{
            put("welcome", "Sveiki atvykę į Mūsų restoraną, prašome, sėstis čia.\nAr atnešti Jums meniu? 'taip' arba 'ne'");
            put("welcome2", pleaseChoose + "'taip' arba 'ne'");
            put("review", "Peržiūrėkite meniu. 'uzsakyti' arba 'iseiti'");
            put("review2", pleaseChoose + "'uzsakyti' arba 'iseiti'");
            put("choose", "Rinkitės: 'int/int int', 'meniu' arba'iseiti'.\nIšsirinkę produktus įveskite 'issirinkau'");
            put("choose2", pleaseChoose + "'int/int int', 'meniu', 'issirinkau' arba 'iseiti'");
            put("finalChoose", Color.UBLACK + "\nRinkitės:" + Color.RESET + Color.GREEN + "'testi'" + Color.RESET + ", " + Color.BLUE + "'persigalvojau'" + Color.RESET + " arba " + Color.RED + "'iseiti' " + Color.RESET);
            put("header", Color.RED_BG + "" + Color.BBLACK + "\n\tSaulėtekio Pizzeria & Wok\n\t" + Color.WHITE + "Restorano meniu:\n");
            put("footer", "\n" + Color.RED_BG + "" + Color.BBLACK + "\n\t" + "\u00a9 Artūras M.\n\n" + Color.RESET);
            put("repeat", "\n\n\tRinkitės iš naujo..");
            put("bye", "\n\tViso gero!");
        }};
    }

    static void println(String message) {
        System.out.println(messages.get(message));
    }

    static void print(String message) {
        System.out.println(messages.get(message) + " ");
    }
}
