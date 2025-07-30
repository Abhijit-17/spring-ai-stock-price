package springai.spring_ai_stock_price.services;

import springai.spring_ai_stock_price.records.Answer;
import springai.spring_ai_stock_price.records.Question;

public interface OpenAIService {

    Answer getAnswer(Question question);
}
