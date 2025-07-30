package springai.spring_ai_stock_price.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    // health check end point
    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }
    

}
