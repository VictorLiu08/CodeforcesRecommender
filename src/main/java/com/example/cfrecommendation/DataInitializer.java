package com.example.cfrecommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Problemset problemset = new Problemset();
        ApplicationData.setApplicationData(problemset);
    }
}