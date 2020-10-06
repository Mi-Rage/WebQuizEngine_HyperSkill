package engine.controller;

import engine.model.Quiz;
import engine.model.Result;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {

    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }


    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id){
        return service.getQuizById(id);
    }

    @GetMapping(path = "/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return service.getAllQuizzesFromStorage();
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz setQuiz(@RequestBody Quiz quiz) {
        return service.addQuizToStorage(quiz);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable int id, @RequestParam int answer){
        return service.solveQuizById(id, answer);
    }
}
