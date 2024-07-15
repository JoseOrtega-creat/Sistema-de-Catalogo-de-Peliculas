package hn.uth.app.util;

import java.util.Scanner;

public class Util {
    private static Scanner scanner = new Scanner(System.in);

    public static String scanString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int scanInt(String message) {
        System.out.print(message);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. " + message);
            }
        }
    }

    public static void print(String message) {
        System.out.println(message);
    }
}

