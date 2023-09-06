package org.example;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String val;
        Scanner sc = new Scanner(System.in);
        int[] priser = new int [24];

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
                    for (int i = 0; i < 24; i++) {
                        System.out.print("Ange pris på elen i hela ören per kW/h för timintervall " + i + "-" + (i + 1) + ": ");
                        int pris = sc.nextInt();
                        sc.nextLine();
                        priser[i] = pris;
                    }
                    System.out.println("Priser är nu lagrade.");
                    break;
                case "2":
                    int min = priser[0];
                    int minIndex = 0;
                    for (int i = 0; i < priser.length; i++) {
                        if(min > priser[i]){
                            min = priser[i];
                            minIndex = i;
                        }
                    }
                    int max = priser[0];
                    int maxIndex = 0;
                    for (int i = 0; i < priser.length; i++) {
                        if(max < priser[i]){
                            max = priser[i];
                            maxIndex = i;
                        }
                    }
                    double mean = Arrays.stream(priser).average().getAsDouble();
                    System.out.println("Det lägsta priset är: " + min + " kr per kW/h mellan klockan " + minIndex + "-" + (minIndex + 1));
                    System.out.println("Det högsta priset är: " + max + " kr per kW/h mellan klockan " + maxIndex + "-" + (maxIndex + 1));
                    System.out.println("Medelvärdet av priset per kW/h under dygnet är: " + mean);
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
