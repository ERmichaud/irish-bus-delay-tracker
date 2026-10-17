package dev.busreliability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@RestController
public class StatusController {


    @GetMapping("/api/status")
    public Map<String, String> status() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "running");
        map.put("application", "Dublin Bus Reliability Tracker");
        return map;
    }

    //temp end point
    @GetMapping("/api/test-nta")
    public String testNta() throws IOException, InterruptedException {
        NtaApiService service = new NtaApiService();
        String url = "https://api.nationaltransport.ie/gtfsr/v2/TripUpdates?format=json";
        String response = service.fetchData(url);

        return response.substring(0, Math.min(500, response.length()));
    }
}
