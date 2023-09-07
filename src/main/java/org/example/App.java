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
                        System.out.print("Ange pris på elen i hela ören per kW/h för timintervall " + i + "-" + (i + 1) + ": ");
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
                    System.out.println("Det lägsta priset är: " + min + " öre per kW/h vid timintervall " + minIndex + "-" + (minIndex + 1));
                    System.out.println("Det högsta priset är: " + max + " öre per kW/h vid timintervall " + maxIndex + "-" + (maxIndex + 1));
                    System.out.println("Medelvärdet av priset per kW/h under dygnet är: " + mean + " öre");
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
                        System.out.println(element.index + "-" + (element.index + 1) + " " + element.value + " öre");
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
