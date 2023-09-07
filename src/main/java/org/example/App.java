package org.example;
import java.util.*;

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
                case "1" -> {
                    for (int i = 0; i < 24; i++) {
                        System.out.printf("Ange pris på elen i hela ören per kW/h för timintervall %02d", i);
                        System.out.printf("-%02d", (i + 1));
                        System.out.print(": ");
                        int pris = sc.nextInt();
                        sc.nextLine();
                        priser[i] = pris;
                    }
                    System.out.println("Priser är nu lagrade.");
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
                    System.out.println("Priser sorterade från dyrast till billigast pris per kW/h inklusive dess timintervall:");
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
                    System.out.println("Den billigaste laddningstiden för 4 timmar i streck är med start klockan " + tid + " och medelpriset för dessa 4 timmar blir " + minMean + " öre.");
                }
                case "e", "E" -> {}
                default -> System.out.println("Fel inmatning, kontrollera och försök igen.");
            }
        } while(!"e".equalsIgnoreCase(val));
        System.out.println("Programmet har avslutats");
    }
}
