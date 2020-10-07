package engine.utils;

import engine.exception.InvalidAnswerException;
import engine.model.Quiz;

public class Utils {

    public static void checkAnswerOptions(Quiz quiz) {
        if(quiz.getOptions() == null) {
            throw new InvalidAnswerException();
        } else {
            int numberOfOptionsInQuiz = quiz.getOptions().length;
            for (Integer eachAnswer : quiz.getAnswer()) {
                if (eachAnswer < 0 || eachAnswer > numberOfOptionsInQuiz) {
                    throw new InvalidAnswerException();
                }
            }
        }
    }

}
