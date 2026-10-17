package dev.busreliability;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonDelayReader {
    public static ArrayList<Integer> extractDelays(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode entities = root.get("entity");
        ArrayList<Integer> delays = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++) {
            JsonNode entity = entities.get(i);
            JsonNode tripUpdate = entity.get("trip_update");
            if(tripUpdate == null) {
                continue;
            }
            JsonNode trip = tripUpdate.get("trip");
            if(trip == null || !trip.hasNonNull("route_id")) {
                continue;
            }
            String routeId = trip.get("route_id").asText();

            //IR - Iarnód Éireann
            //10000 GREEN - Luas green line
            //10000 RED - Luas red line
            boolean isBus =
                    routeId.startsWith("1 ") || //dublin bus
                            routeId.startsWith("2 ") || //bus Éireann
                            routeId.startsWith("3 ") || //Go-ahead ireland
                            routeId.startsWith("03C ") || //Go-ahead
                            routeId.startsWith("WFRD "); //waterford bus Éireann
            if(!isBus) {
                continue;
            }
            JsonNode stopUpdates = tripUpdate.get("stop_time_update");
            if(stopUpdates == null) {
                continue;
            }
            for(int j = 0; j < stopUpdates.size(); j++) {
                JsonNode arrival = stopUpdates.get(j).get("arrival");
                if(arrival != null && arrival.hasNonNull("delay")) {
                    int delay = arrival.get("delay").asInt();
                    delays.add(delay);
                }
            }
        }

        return delays;
    }

}
