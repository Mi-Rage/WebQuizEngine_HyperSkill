package engine.controller;

import engine.model.Quiz;
import engine.model.Result;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    public Quiz setQuiz(@Valid @NotNull @RequestBody Quiz quiz) {
        return service.addQuizToStorage(quiz);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve", produces = APPLICATION_JSON_VALUE)
    public Result solveQuiz(@PathVariable int id, @RequestBody Quiz answer){
        return service.solveQuizById(id, answer.getAnswer());
    }
}
