package com.example.cfrecommendation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Problemset {

    ArrayList<Problem> problemSet;

    public Problemset() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://codeforces.com/api/problemset.problems"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        problemSet = mapper.readValue(response.body(), CodeforcesProblemsetResponse.class).result.problems;
    }

    public String toString() {
        return problemSet.toString();
    }

}
