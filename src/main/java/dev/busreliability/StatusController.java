package dev.busreliability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@RestController
public class StatusController {

    private final NtaApiService service;

    public StatusController(NtaApiService service) {
        this.service = service;
    }


    @GetMapping("/api/status")
    public Map<String, String> status() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "running");
        map.put("application", "Dublin Bus Reliability Tracker");
        return map;
    }

    //temp end point
    @GetMapping("/api/delays")
    public Map<String, Object> getDelays(@RequestParam(required = false) String route) throws IOException, InterruptedException {
        String url = "https://api.nationaltransport.ie/gtfsr/v2/TripUpdates?format=json";
        String response = service.fetchData(url);

        ArrayList<BusDelay> delays = JsonDelayReader.extractDelays(response);
        ArrayList<Integer> delayValues = new ArrayList<>();
        for(BusDelay busDelay : delays) {
            String busRoute = busDelay.getRouteId().split(" ")[1];
            if(route == null || busRoute.equalsIgnoreCase(route)) {
                delayValues.add(busDelay.getDelaySeconds());
            }
        }
        double averageDelay = DelayCalculator.calculateAverage(delayValues);

        Map<String, Object> result = new HashMap<>();
        result.put("totalPredictions", delayValues.size());
        result.put("averagePredictedDelayMinutes", Math.round(averageDelay * 100.0) / 100.0);
        return result;
    }
}
