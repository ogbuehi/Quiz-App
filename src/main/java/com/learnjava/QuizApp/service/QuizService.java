package com.learnjava.QuizApp.service;

import com.learnjava.QuizApp.model.Question;
import com.learnjava.QuizApp.model.QuestionWrapper;
import com.learnjava.QuizApp.model.Quiz;
import com.learnjava.QuizApp.model.QuizResponse;
import com.learnjava.QuizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){
        List<Question> questions = quizRepository.getRandomQuestionByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestion();
        List<QuestionWrapper> questionsFromUser = new ArrayList<>();
        for (Question q:
             questionsFromDB) {
            QuestionWrapper wrapper = new QuestionWrapper(q.getQuestionTitle(),q.getOption1(),
                    q.getOption2(),q.getOption3(),q.getOption4());
            questionsFromUser.add(wrapper);
        }
        return new ResponseEntity<>(questionsFromUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestion();
        int right = 0, i = 0;
        for (QuizResponse response:responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
