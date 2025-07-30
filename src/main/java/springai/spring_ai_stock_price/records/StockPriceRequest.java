package springai.spring_ai_stock_price.records;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Stock Price Request DTO")
public record StockPriceRequest(
    @JsonProperty(required = true, value = "ticker")
    @JsonPropertyDescription("The stock ticker symbol or stock name for which the price is requested")
    String ticker) {

}
