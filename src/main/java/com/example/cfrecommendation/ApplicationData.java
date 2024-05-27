package com.example.cfrecommendation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ApplicationData {
    private static Problemset problemSet;

    public static Problemset getApplicationData() {
        return problemSet;
    }

    public static void setApplicationData(Problemset problemSet) {
        ApplicationData.problemSet = problemSet;
    }
}