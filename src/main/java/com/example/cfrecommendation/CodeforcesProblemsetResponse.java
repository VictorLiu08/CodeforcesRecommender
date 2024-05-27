package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeforcesProblemsetResponse {
    public Result result;

    public static class Result {
        public ArrayList<Problem> problems;
        public ArrayList<ProblemStatistics> problemStatistics;
    }

}
