package engine.controller;

import engine.model.Quiz;
import engine.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    Quiz quiz = new Quiz("The Java Logo", "What is depicted on the Java logo?",
            new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"});

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz(){
        return quiz;
    }

    @PostMapping(path = "/api/quiz")
    public Result solveQuiz(@RequestParam int answer){
        if(answer == 2) {
            return Result.success();
        } else {
            return Result.wrong();
        }
    }


}
