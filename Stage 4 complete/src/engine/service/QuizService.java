package engine.service;

import engine.exception.QuizNotFoundException;
import engine.model.dto.QuizDto;
import engine.model.dto.ResultDto;
import engine.model.jpa.Quiz;
import engine.repository.QuizRepository;
import engine.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDto addQuizToStorage(QuizDto quizDto) {
        Utils.checkAnswerOptions(quizDto);
        Quiz quizEntity = Utils.convertQuizDtoToEntity(quizDto);
        int id = quizRepository.save(quizEntity).getId();
        quizDto.setId(id);
        return quizDto;
    }

    public List<QuizDto> getAllQuizzesFromStorage() {
        List<QuizDto> quizzesDto = new ArrayList<>();
        Iterable<Quiz> quizEntityList = quizRepository.findAll();
        for(Quiz each : quizEntityList) {
            QuizDto quizDto = Utils.convertEntityToQuizDto(each);
            quizzesDto.add(quizDto);
        }
        return quizzesDto;
    }

    public QuizDto getQuizById(int id) {
        Quiz quizEntity = findById(id);
        return Utils.convertEntityToQuizDto(quizEntity);
    }

    public Quiz findById(int id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if (optionalQuiz.isEmpty()) {
            throw new QuizNotFoundException();
        } else {
            return optionalQuiz.get();
        }
    }

    public ResultDto solveQuizById(int id, Set<Integer> answer) {
        Quiz quizEntity = findById(id);
        Set<Integer> fromRepositoryAnswer = Utils.getIndexOfAnswer(quizEntity.getOptions());
        return Arrays.equals(fromRepositoryAnswer.toArray(), answer.toArray()) ? ResultDto.success() : ResultDto.wrong();
    }

}

