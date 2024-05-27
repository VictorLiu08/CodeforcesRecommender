package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSubmissions {

    public ArrayList<Submission> result;

}
