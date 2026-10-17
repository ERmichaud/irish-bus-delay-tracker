package dev.busreliability;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class DelayCalculatorTest {

    @Test
    void testPositiveDelays() {
        ArrayList<Integer> delays = new ArrayList<>();
        delays.add(60);
        delays.add(120);
        delays.add(180);
        double delay = DelayCalculator.calculateAverage(delays);
        assertEquals(delay, 2.0);
    }

    @Test
    void testNegativeDelays() {
        ArrayList<Integer> delays = new ArrayList<>();
        delays.add(-60);
        delays.add(-120);
        delays.add(-180);
        double delay = DelayCalculator.calculateAverage(delays);
        assertEquals(delay, -2.0);
    }

    @Test
    void testEmptyList() {
        ArrayList<Integer> delays = new ArrayList<>();
        double delay = DelayCalculator.calculateAverage(delays);
        assertEquals(delay, 0);
    }

    @Test
    void testSingleDelay() {
        ArrayList<Integer> delays = new ArrayList<>();
        delays.add(480);
        double delay = DelayCalculator.calculateAverage(delays);
        assertEquals(delay, 8);
    }

    @Test
    void testMixedDelays() {
        ArrayList<Integer> delays = new ArrayList<>();
        delays.add(240);
        delays.add(-60);
        delays.add(180);
        double delay = DelayCalculator.calculateAverage(delays);
        assertEquals(delay, 2.0);
    }
}
