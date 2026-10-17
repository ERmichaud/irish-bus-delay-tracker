package dev.busreliability;

import java.util.ArrayList;
public class DelayCalculator {

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
