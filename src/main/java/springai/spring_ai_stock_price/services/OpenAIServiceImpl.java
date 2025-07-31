package springai.spring_ai_stock_price.services;

import springai.spring_ai_stock_price.functions.StockPriceFunction;
import springai.spring_ai_stock_price.records.Answer;
import springai.spring_ai_stock_price.records.Question;
import springai.spring_ai_stock_price.records.StockPriceRequest;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ai.tool.execution.DefaultToolCallResultConverter;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiChatModel openAiChatModel;

    @Value("${asb.aiapp.apiNinjasKey}")
    private String apiNinjasKey;
   
    @Override
    public Answer getAnswer(Question question) {

        StockPriceFunction stockPriceFunction = new StockPriceFunction(apiNinjasKey);

        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .toolCallbacks(List.of(
                    FunctionToolCallback.builder("StockPrice", stockPriceFunction)
                        .description("Fetch stock price based on ticker symbol or stock name")
                        .inputType(StockPriceRequest.class)
                        .toolCallResultConverter(new DefaultToolCallResultConverter())
                        .build()
                ))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        String systemPrompt = """
            You are a helpful assistant that can answer questions about stock prices.
            User can ask about stock prices by providing a ticker symbol or stock name.
            If the user asks for a stock price, you will call the StockPrice function with the ticker symbol or stock name.
            If the user asks a general question apart from stock prices, you will respond with dummy answer ("I only assist with stock prices. Please ask a stock-related question.").
            While answering the question you will recieve the ticker or stock name, sotocks full name, current price, the currency in which the stock price is quoted, the stock exchange where the stock is listed or traded, and the last updated timestamp in EPOC.
            Change the Epoch timestamp to human readable format and include it in your response and dont say that it was updated recently bu give actual date and time.
            Reply with a freindly tone and in a paragrap not in bullet points.
                """;
        Message systemMessage = new SystemPromptTemplate(systemPrompt).createMessage();

        ChatResponse chatResponse = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), chatOptions));
                
        return new Answer(chatResponse.getResult().getOutput().getText());
    }

}
