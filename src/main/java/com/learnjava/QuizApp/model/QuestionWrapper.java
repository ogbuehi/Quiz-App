package com.learnjava.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
