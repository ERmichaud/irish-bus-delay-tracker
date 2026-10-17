package dev.busreliability;

import java.util.ArrayList;
public class DelayCalculator {
    public static void main(String[] args) {
        ArrayList<Integer> delays = new ArrayList<>();
        delays.add(2086);
        delays.add(2929);
        delays.add(2854);
        double average =  calculateAverage(delays);
        System.out.printf("New avg: %.2f", average);
    }
    public static double calculateAverage(ArrayList<Integer> delays) {
        if (delays.isEmpty()) {
            return 0;
        }
        double average = 0;
        for(int i = 0; i < delays.size(); i++) {
            average +=  (double) delays.get(i) / 60;
        }
        average /= delays.size();
        return average;
    }
}
