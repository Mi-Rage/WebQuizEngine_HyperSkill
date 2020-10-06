package engine.service;

import engine.exception.QuizNotFoundException;
import engine.model.Quiz;
import engine.model.Result;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizService {
    Map<Integer, Quiz> storage = new HashMap<>();
    AtomicInteger idGenerator = new AtomicInteger();


    public Quiz addQuizToStorage(Quiz quiz) {
        int id = idGenerator.incrementAndGet();
        quiz.setId(id);
        storage.putIfAbsent(id, quiz);
        return quiz;
    }

    public List<Quiz> getAllQuizzesFromStorage() {
        return new ArrayList<>(storage.values());
    }

    public Quiz getQuizById(int id) {
        Quiz quiz = storage.get(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        } else {
            return quiz;
        }
    }

    public Result solveQuizById(int id, int answer) {
        Quiz quiz = storage.get(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        } else {
            return quiz.getAnswer() == answer ? Result.success() : Result.wrong();
        }
    }

}
