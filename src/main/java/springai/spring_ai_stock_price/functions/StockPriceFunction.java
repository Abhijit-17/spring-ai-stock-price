package springai.spring_ai_stock_price.functions;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import java.util.function.Function;

import org.springframework.web.client.RestClient;

import springai.spring_ai_stock_price.records.StockPriceRequest;
import springai.spring_ai_stock_price.records.StockPriceResponse;



@JsonClassDescription("StockPriceFunction to fetch Stock Price based on ticker symbol or stock name")
public class StockPriceFunction implements Function<StockPriceRequest, StockPriceResponse> {

    public static final String STOCK_PRICE_URL = "https://api.api-ninjas.com/v1/stockprice";

    private final String apiNinjasKey;

    // Constructor to inject the API key
    public StockPriceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }

    @Override
    public StockPriceResponse apply(StockPriceRequest request) {

        System.out.println("StockPriceRequest: " + request );

        RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_PRICE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Content-Type", "application/json");
                    httpHeaders.set("Accept", "application/json");
                })
                .build();
        
        StockPriceResponse response = restClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.queryParam("ticker", request.ticker());
                    return uriBuilder.build();
                }).retrieve().body(StockPriceResponse.class);

        System.out.println("StockPriceResponse: " + response);

        return response;
    }

}
