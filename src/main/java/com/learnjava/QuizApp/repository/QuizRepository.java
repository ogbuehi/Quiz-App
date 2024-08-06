package com.learnjava.QuizApp.repository;

import com.learnjava.QuizApp.model.Question;
import com.learnjava.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {

    @Query(value = "SELECT * FROM question q WHERE q.category:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> getRandomQuestionByCategory(String category, int numQ);
}
