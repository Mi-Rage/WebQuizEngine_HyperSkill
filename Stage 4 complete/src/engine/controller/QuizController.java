package engine.controller;

import engine.model.dto.QuizDto;
import engine.model.dto.ResultDto;
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
    public QuizDto getQuiz(@PathVariable int id){
        return service.getQuizById(id);
    }

    @GetMapping(path = "/api/quizzes")
    public List<QuizDto> getAllQuizzes() {
        return service.getAllQuizzesFromStorage();
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public QuizDto setQuiz(@Valid @NotNull @RequestBody QuizDto quizDto) {
        return service.addQuizToStorage(quizDto);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve", produces = APPLICATION_JSON_VALUE)
    public ResultDto solveQuiz(@PathVariable int id, @RequestBody QuizDto answer){
        System.out.println(answer.getAnswer().toString());
        return service.solveQuizById(id, answer.getAnswer());
    }

}
