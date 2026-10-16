public class DelayCalculator {
    public static void main(String[] args) {
        int[] delays = {2086, 2929, 2854};
        double avg = 0;
        for(int i = 0; i < delays.length; i++) {
            double cur = (double) delays[i] / 60;
            int stop = i + 1;
            System.out.printf("Stop %d: %.2f minutes late%n", stop, cur);
            avg += cur;
        }
        avg = avg/delays.length;
        System.out.printf("Average arrival delay: %.2f minutes%n", avg);
    }
}
