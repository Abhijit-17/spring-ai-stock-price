package springai.spring_ai_stock_price.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springai.spring_ai_stock_price.records.Answer;
import springai.spring_ai_stock_price.records.Question;
import springai.spring_ai_stock_price.services.OpenAIServiceImpl;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final OpenAIServiceImpl openAIService;

    public QuestionController(OpenAIServiceImpl openAIService) {
        this.openAIService = openAIService;
    }

    // health check end point
    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }

    // question endpoint
    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
}    


