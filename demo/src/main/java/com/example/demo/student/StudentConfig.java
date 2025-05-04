package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

//import org.springframework.context.annotation.Configuration;
//@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository){
        return args -> {
            Student roman = new Student(
                "Roman",
                LocalDate.of(1999, Month.SEPTEMBER, 27),
                "romanalejandro238@gmail.com"
            );
            Student alejandro = new Student(
                "alejandro",
                LocalDate.of(2000, Month.SEPTEMBER, 27),
                "alex@gmail.com"
            );

            repository.saveAll(List.of(roman,alejandro));
        };
    }
    
}
