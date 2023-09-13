package org.example;
import java.util.*;

public class App {

    public static void main(String[] args) {
        String val;
        Scanner sc = new Scanner(System.in);
        int[] priser = new int [24];

        do {
            String menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;
            System.out.print(menu);
            val = sc.nextLine();

            switch (val) {
                case "1" -> {
                    System.out.print("Ange pris på elen i hela ören per kW/h för timintervall:  \n");
                    for (int i = 0; i < 24; i++) {
                        System.out.printf("%02d", i);
                        System.out.printf("-%02d", (i + 1));
                        System.out.print(": ");
                        int pris = sc.nextInt();
                        sc.nextLine();
                        priser[i] = pris;
                    }
                    System.out.print("Priser är nu lagrade.\n");
                }
                case "2" -> {
                    int min = priser[0];
                    int minIndex = 0;
                    for (int i = 0; i < priser.length; i++) {
                        if (min > priser[i]) {
                            min = priser[i];
                            minIndex = i;
                        }
                    }
                    int max = priser[0];
                    int maxIndex = 0;
                    for (int i = 0; i < priser.length; i++) {
                        if (max < priser[i]) {
                            max = priser[i];
                            maxIndex = i;
                        }
                    }
                    double mean = Arrays.stream(priser).average().getAsDouble();
                    System.out.printf("Lägsta pris: %02d", minIndex);
                    System.out.printf("-%02d", (minIndex + 1));
                    System.out.printf(", %d öre/kWh \n", min);
                    System.out.printf("Högsta pris: %02d", maxIndex);
                    System.out.printf("-%02d", (maxIndex + 1));
                    System.out.printf(", %d öre/kWh \n", max);
                    System.out.printf("Medelpris: %.2f", mean);
                    System.out.print(" öre/kWh\n");
                }
                case "3" -> {
                    System.out.print("Priser sorterade från dyrast till billigast pris per kW/h inklusive dess timintervall:\n");
                    List<Sort> elements = new ArrayList<>();
                    for (int i = 0; i < priser.length; i++) {
                        elements.add(new Sort(i, priser[i]));
                    }
                    Collections.sort(elements);
                    for (Sort element : elements) {
                        System.out.printf("%02d", element.index);
                        System.out.printf("-%02d", (element.index + 1));
                        System.out.print(" " + element.value + " öre\n");
                    }
                }
                case "4" -> {
                    double minMean = Double.MAX_VALUE;
                    int minMeanIndex = -1;
                    int tid = 0;
                    for (int i = 0; i <= priser.length - 4; i++) {
                        double sum = 0;
                        for (int j = i; j < i + 4; j++) {
                            sum += priser[j];
                        }
                        double mean = sum / 4;
                        if (mean < minMean) {
                            minMean = mean;
                            minMeanIndex = i;
                        }
                    }
                    for (int i = minMeanIndex; i < minMeanIndex + 4; i++) {
                        tid = i - 3;
                    }
                    System.out.printf("Påbörja laddning klockan %02d\n", tid);
                    System.out.printf("Medelpris 4h: %,.1f", minMean);
                    System.out.print(" öre/kWh\n");
                }
                case "e", "E" -> {}
                default -> System.out.print("Fel inmatning, kontrollera och försök igen.\n");
            }
        } while(!"e".equalsIgnoreCase(val));
        System.out.print("Programmet har avslutats\n");
    }
}
