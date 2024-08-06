package com.learnjava.QuizApp.controller;

import com.learnjava.QuizApp.model.Question;
import com.learnjava.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Question")
@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
        return questionService.getByCategory(category);
    }
    @PostMapping("/new")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
