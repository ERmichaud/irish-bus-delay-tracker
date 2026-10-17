package dev.busreliability;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonDelayReader {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("data/sample-trip-updates.json");
        JsonNode root = mapper.readTree(file);
        JsonNode entities = root.get("entity");
        JsonNode firstEntity = entities.get(0);
        JsonNode tripUpdate = firstEntity.get("trip_update");
        JsonNode trip = tripUpdate.get("trip");
        String tripID = trip.get("trip_id").asText();
        System.out.println(tripID);

        JsonNode stopUpdates = tripUpdate.get("stop_time_update");
        ArrayList<Integer> delays = new ArrayList<>();
        for(int i = 0; i < stopUpdates.size(); i++) {
            int stopSequence = stopUpdates.get(i).get("stop_sequence").asInt();
            JsonNode arrival = stopUpdates.get(i).get("arrival");
            if(arrival != null && arrival.hasNonNull("delay")) {
                int delay = arrival.get("delay").asInt();
                delays.add(delay);
            }
        }
        if(!delays.isEmpty()) {
            double average = DelayCalculator.calculateAverage(delays);
            System.out.printf("Average arrival delay: %.2f minutes%n", average);
        } else {
            System.out.println("No arrival data available");
        }

    }

}
