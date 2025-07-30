package springai.spring_ai_stock_price.records;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Stock Price Response DTO")
public record StockPriceResponse(
    @JsonProperty("ticker")
    @JsonPropertyDescription("The stock ticker symbol or stock name for which the price is requested")
    String ticker,

    @JsonProperty("name")
    @JsonPropertyDescription("The full name of the stock or the company")
    String name,

    @JsonProperty("price")
    @JsonPropertyDescription("The current market price of the stock or ticker symbol")
    BigDecimal price,

    @JsonProperty("exchange")
    @JsonPropertyDescription("The stock exchange where the stock is listed or traded")
    String exchange,

    @JsonProperty("updated")
    @JsonPropertyDescription("The Epoch/Unix timestamp when the stock price was last updated")
    Long updated,

    @JsonProperty("currency")
    @JsonPropertyDescription("The currency in which the stock price is quoted")
    String currency
) {

}
