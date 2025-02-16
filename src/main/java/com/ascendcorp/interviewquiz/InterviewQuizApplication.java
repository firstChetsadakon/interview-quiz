package com.ascendcorp.interviewquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InterviewQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewQuizApplication.class, args);
    }

}
