package dev.busreliability;

import org.springframework.stereotype.Service;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

@Service
public class NtaApiService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String apiKey = System.getenv("NTA_API_KEY");
    private String cachedResponse;
    private long lastFetchTime = 0;

    public HttpRequest buildRequest(String url) {
        if(!hasApiKey()) {
            throw new IllegalStateException("NTA_API_KEY environment variable missing");
        }
        return HttpRequest.newBuilder().uri(URI.create(url)).header("x-api-key", apiKey).GET().build();
    }

    public String fetchData(String url) throws IOException, InterruptedException {

        long currentTime = System.currentTimeMillis();
        if (cachedResponse != null && currentTime - lastFetchTime <60_000){
            return cachedResponse;
        }

        HttpRequest request = buildRequest(url);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP status: " + response.statusCode());
        if(response.statusCode() != 200) {
            throw new IOException("NTA API returned HTTP " + response.statusCode());
        }

        cachedResponse = response.body();
        lastFetchTime = System.currentTimeMillis();
        return cachedResponse;
    }

    public boolean hasApiKey() {
        return apiKey != null && !apiKey.isBlank();
    }
}
