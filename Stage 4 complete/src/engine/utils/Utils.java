package engine.utils;

import engine.exception.InvalidAnswerException;
import engine.model.dto.QuizDto;
import engine.model.jpa.Option;
import engine.model.jpa.Quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static void checkAnswerOptions(QuizDto quizDto) {
        if(quizDto.getOptions() == null) {
            throw new InvalidAnswerException();
        } else {
            int numberOfOptionsInQuiz = quizDto.getOptions().size();
            for (Integer eachAnswer : quizDto.getAnswer()) {
                if (eachAnswer < 0 || eachAnswer > numberOfOptionsInQuiz) {
                    throw new InvalidAnswerException();
                }
            }
        }
    }

    public static Quiz convertQuizDtoToEntity(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizDto.getId());
        quiz.setTitle(quizDto.getTitle());
        quiz.setText(quizDto.getText());
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < quizDto.getOptions().size(); i++) {
            Option option = new Option();
            option.setText(quizDto.getOptions().get(i));
            if (quizDto.getAnswer().contains(i)) {
                option.setAnswer(true);
            }
            options.add(option);
        }
        quiz.setOptions(options);
        return quiz;
    }

    public static QuizDto convertEntityToQuizDto(Quiz quizEntity) {
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quizEntity.getId());
        quizDto.setTitle(quizEntity.getTitle());
        quizDto.setText(quizEntity.getText());
        quizDto.setOptions(quizEntity.getOptions().stream()
        .map(Option::getText)
        .collect(Collectors.toList()));
        return quizDto;
    }

    public static Set<Integer> getIndexOfAnswer(List<Option> options) {
        Set<Integer> answer = new HashSet<>();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getAnswer()) {
                answer.add(i);
            }
        }
        return answer;
    }

}
