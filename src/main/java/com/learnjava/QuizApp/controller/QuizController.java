package com.learnjava.QuizApp.controller;

import com.learnjava.QuizApp.model.Question;
import com.learnjava.QuizApp.model.QuestionWrapper;
import com.learnjava.QuizApp.model.Quiz;
import com.learnjava.QuizApp.model.QuizResponse;
import com.learnjava.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, String title){
        return quizService.createQuiz(category, numQ, title);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable  Integer id){
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> response){
        return quizService.calculateResult(id, response);
    }
}
