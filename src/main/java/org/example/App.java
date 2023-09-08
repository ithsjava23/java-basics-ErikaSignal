package org.example;
import java.util.*;

public class App {

    public static void main(String[] args) {
        String val;
        Scanner sc = new Scanner(System.in);
        int[] priser = new int [24];

        do {
            System.out.print("Elpriser\n");
            System.out.print("========\n");
            System.out.print("1. Inmatning\n");
            System.out.print("2. Min, max och medel\n");
            System.out.print("3. Sortera\n");
            System.out.print("4. Bästa laddningstid (4h)\n");
            System.out.print("e. Avsluta\n");
            val = sc.nextLine();

            switch (val) {
                case "1" -> {
                    for (int i = 0; i < 24; i++) {
                        System.out.printf("Ange pris på elen i hela ören per kW/h för timintervall %02d", i);
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
                    System.out.print("Det lägsta priset är " + min);
                    System.out.printf(" öre per kW/h vid timintervall %02d", minIndex);
                    System.out.printf("-%02d\n", (minIndex + 1));
                    System.out.print("Det lägsta priset är " + max);
                    System.out.printf(" öre per kW/h vid timintervall %02d", maxIndex);
                    System.out.printf("-%02d\n", (maxIndex + 1));
                    System.out.printf("Medelvärdet av alla priser under dygnet är %.2f", mean);
                    System.out.print(" öre\n");
                }
                case "3" -> {
                    System.out.print("Priser sorterade från dyrast till billigast pris per kW/h inklusive dess timintervall:\n");
                    List<Sort> elements = new ArrayList<>();
                    for (int i = 0; i < priser.length; i++) {
                        elements.add(new Sort(i, priser[i]));
                    }
                    Collections.sort(elements);
                    Collections.reverse(elements);
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
                    System.out.print("Den billigaste laddningstiden för 4 timmar i streck är med start klockan " + tid + " och medelpriset för dessa 4 timmar blir " + minMean + " öre.\n");
                }
                case "e", "E" -> {}
                default -> System.out.print("Fel inmatning, kontrollera och försök igen.\n");
            }
        } while(!"e".equalsIgnoreCase(val));
        System.out.print("Programmet har avslutats\n");
    }
}
