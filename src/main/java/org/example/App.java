package org.example;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String val;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Elpriser");
            System.out.println("========");
            System.out.println("1. Inmatning");
            System.out.println("2. Min, max och medel");
            System.out.println("3. Sortera");
            System.out.println("4. Bästa laddningstid (4h)");
            System.out.println("e. Avsluta");
            val = sc.nextLine();

            switch (val) {
                case "1":
                    int[] priser = new int [24];
                    for (int i = 0; i < 24; i++) {
                        System.out.print("Ange pris på elen i hela ören per kW/h för timintervall " + i + "-" + (i + 1) + ": ");
                        int pris = sc.nextInt();
                        sc.nextLine();
                        priser[i] = pris;
                    }
                    System.out.println("Priser är nu lagrade.");
                    break;
                case "2":
                    //ToDo
                    break;
                case "3":
                    //ToDo: Sortera koden efter dyrast till billigast.
                    break;
                case "4":
                    //ToDo: Bästa/billigaste 4 timmar i sträck.
                    break;
                case "e", "E":
                    break;
                default:
                    System.out.println("Fel inmatning, kontrollera och försök igen.");
            }
        } while(!"e".equalsIgnoreCase(val));
        System.out.println("avsluta");
    }
}
