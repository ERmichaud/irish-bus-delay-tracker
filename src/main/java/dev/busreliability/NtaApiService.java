package dev.busreliability;

import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class NtaApiService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String apiKey = System.getenv("NTA_API_KEY");
    public HttpRequest buildRequest(String url) {
        if(!hasApiKey()) {
            throw new IllegalStateException("NTA_API_KEY environment variabel missing");
        }
        return HttpRequest.newBuilder().uri(URI.create(url)).header("x-api-key", apiKey).GET().build();
    }

    public String fetchData(String url) throws IOException, InterruptedException {
        HttpRequest request = buildRequest(url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP status: " + response.statusCode());
        if(response.statusCode() != 200) {
            throw new IOException("NTA API returned HTTP " + response.statusCode());
        }

        return response.body();
    }

    public boolean hasApiKey() {
        return apiKey != null && !apiKey.isBlank();
    }
}
