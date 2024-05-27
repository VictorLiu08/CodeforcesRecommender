package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Controller
public class AppController {

    static ArrayList<Problem> displayedProblems = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/process")
    public ModelAndView process(HttpSession session, @RequestParam String handle) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://codeforces.com/api/user.status?handle=" + handle))
                .build();
        session.setAttribute("currentHandle", handle);
        if (client.send(request, HttpResponse.BodyHandlers.ofString()).body().charAt(11) == 'F') {
            ModelAndView modelAndView = new ModelAndView("HandleDisplayFragment");
            modelAndView.addObject("handle", "User Not Found");
            return modelAndView;
        }
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ArrayList<Submission> jsonFile = mapper.readValue(response.body(), UserSubmissions.class).result;
        session.setAttribute("userSubmissions", jsonFile);
        ModelAndView modelAndView = new ModelAndView("HandleDisplayFragment");
        modelAndView.addObject("handle", handle);
        modelAndView.addObject("rating", getRating(handle));
        return modelAndView;
    }

    @PostMapping("/RecommendProblem")
    @SuppressWarnings("unchecked")
    public ModelAndView RecommendProblem(HttpSession session) {
        Problemset problemset = ApplicationData.getApplicationData();
        int currentRating = getRating(session.getAttribute("currentHandle").toString());
        ArrayList<Submission> userSubmissions = (ArrayList<Submission>) session.getAttribute("userSubmissions");
        HashMap<String, Integer> tagCorrect = new HashMap<>();
        HashMap<String, Integer> tagIncorrect = new HashMap<>();
        HashSet<String> problemsSolved = new HashSet<>();
        for (Submission submission : userSubmissions) {
            if (submission.verdict.equals("OK")) problemsSolved.add(submission.problem.contestId + submission.problem.index);
            if (submission.problem.rating == null) continue;
            if (Integer.parseInt(submission.problem.rating) < currentRating - 100 || Integer.parseInt(submission.problem.rating) > currentRating + 400) continue;
            if (submission.verdict.equals("OK")) {
                for (String tag : submission.problem.tags) {
                    tagCorrect.put(tag, tagCorrect.getOrDefault(tag, 0) + 1);
                }
            } else {
                for (String tag : submission.problem.tags) {
                    tagIncorrect.put(tag, tagIncorrect.getOrDefault(tag, 0) + 1);
                }
            }
        }
        ArrayList<Problem> fitProblems = new ArrayList<>();
        HashSet<String> fitProblemsSet = new HashSet<>();
        for (Submission submission : userSubmissions) {
            if (!submission.verdict.equals("OK")) {
                if (!problemsSolved.contains(submission.problem.contestId + submission.problem.index)) {
                    if (!fitProblemsSet.contains(submission.problem.contestId + submission.problem.index)) {
                        fitProblems.add(submission.problem);
                        fitProblemsSet.add(submission.problem.contestId + submission.problem.index);
                    }
                }
            }
        }
        HashMap<String, Integer> tagFrequency = new HashMap<>();
        for (Problem problem : problemset.problemSet) {
            if (problem.rating == null) continue;
            if (Integer.parseInt(problem.rating) < currentRating - 100 || Integer.parseInt(problem.rating) > currentRating + 400) continue;
            for (String tag : problem.tags) {
                tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            }
        }
        HashMap<String, Double> tagScore = new HashMap<>();
        ArrayList<Double> scores = new ArrayList<>();
        for (String tag : tagFrequency.keySet()) {
            double correct = tagCorrect.getOrDefault(tag, 0);
            double incorrect = tagIncorrect.getOrDefault(tag, 0);
            double percentSolved = correct / tagFrequency.get(tag);
            double triesPer = correct == 0 ? Integer.MAX_VALUE : (correct + incorrect) / correct;
            tagScore.put(tag, (1 - percentSolved) * triesPer);
            scores.add((1 - percentSolved) * triesPer);
        }
        Collections.sort(scores);
        HashSet<String> topTags = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            for (String tag : tagScore.keySet()) {
                if (tagScore.get(tag).equals(scores.get(i))) {
                    topTags.add(tag);
                }
            }
        }
        for (int i = 0; i < problemset.problemSet.size(); i++) {
            if (problemsSolved.contains(problemset.problemSet.get(i).contestId + problemset.problemSet.get(i).index)) continue;
            Problem problem = problemset.problemSet.get(i);
            if (problem.rating == null) continue;
            if (Integer.parseInt(problem.rating) >= currentRating - 100 && Integer.parseInt(problem.rating) <= currentRating + 400) {
                for (String tag : problem.tags) {
                    if (topTags.contains(tag)) {
                        fitProblems.add(problem);
                        break;
                    }
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView("ProblemFragment");
        int randomIndex = (int) (Math.random() * fitProblems.size());
        displayedProblems.add(fitProblems.get(randomIndex));
        if (displayedProblems.size() > 5) {
            displayedProblems.remove(0);
        }
        modelAndView.addObject("displayedProblems", displayedProblems);
        return modelAndView;
    }

    @PostMapping("/GenerateProblem")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public ModelAndView GenerateProblem(@RequestParam String tags, @RequestParam String minRating, @RequestParam String maxRating) throws Exception {
        Problemset problemset = ApplicationData.getApplicationData();
        ModelAndView modelAndView = new ModelAndView("ProblemFragment");
        ArrayList<Integer> fitTags = new ArrayList<>();
        int minrating = Integer.parseInt(minRating);
        int maxrating = Integer.parseInt(maxRating);
        for (int i = 0; i < problemset.problemSet.size(); i++) {
            if (problemset.problemSet.get(i).rating == null) continue;
            for (String tag : problemset.problemSet.get(i).tags) {
                if (tags.contains(tag) && Integer.parseInt(problemset.problemSet.get(i).rating) >= minrating && Integer.parseInt(problemset.problemSet.get(i).rating) <= maxrating) {
                    fitTags.add(i);
                    break;
                }
            }
            if (tags.isEmpty() && Integer.parseInt(problemset.problemSet.get(i).rating) >= minrating && Integer.parseInt(problemset.problemSet.get(i).rating) <= maxrating) {
                fitTags.add(i);
            }
        }
        int randomIndex = fitTags.get((int) (Math.random() * fitTags.size()));
        displayedProblems.add(problemset.problemSet.get(randomIndex));
        if (displayedProblems.size() > 5) {
            displayedProblems.remove(0);
        }
        modelAndView.addObject("displayedProblems", displayedProblems);
        return modelAndView;
    }

    public static int getRating(String handle) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://codeforces.com/api/user.rating?handle=" + handle))
                .build();
        String body = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
        int index = body.lastIndexOf(":");
        if (index == -1) return 800;
        return Integer.parseInt(body.substring(index + 1, body.indexOf("}", index)));
    }

}
