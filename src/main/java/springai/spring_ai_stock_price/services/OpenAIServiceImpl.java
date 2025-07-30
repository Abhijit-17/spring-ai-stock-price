package springai.spring_ai_stock_price.services;

import springai.spring_ai_stock_price.records.Answer;
import springai.spring_ai_stock_price.records.Question;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Override
    public Answer getAnswer(Question question) {
        return new Answer("This is a dummy answer for the question: " + question.question());
    }

}
