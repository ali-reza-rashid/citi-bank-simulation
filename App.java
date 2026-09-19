import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Main {
    private static class RequestResult {
        public final BigDecimal price;
        public final Instant timestamp;

        public RequestResult(BigDecimal givenPrice, Instant timeOccur) {
            this.price = givenPrice;
            this.timestamp = timeOccur;
        }
    }

    private static Queue<RequestResult> results = new ArrayDeque<>();

    BigDecimal getPriceFromResponse(String jsonResponse) {
        String price = jsonResponse
                .replace("{\"price\":\"", "")
                .replace("\"}", "");

        return BigDecimal.valueOf(Double.parseDouble(price));
    }

    static void processNewRequest(String url) {
        Instant timestamp = Instant.now();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BigDecimal price = new Main().getPriceFromResponse(response.body());
            System.out.println(price);
            System.out.println(timestamp);
            results.add(new RequestResult(price, timestamp));
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred during the API request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void main(String[] args) {
        String apiKey = System.getenv("TWELVE_API_KEY");
        String symbol = "DIA";
        String url = "https://api.twelvedata.com/price?symbol=" + symbol + "&apikey=" + apiKey;

        while (true) {
            processNewRequest(url);

            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("The interval was interrupted");
                break;
            }
        }
    }
}
