package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Problem {
    String contestId;
    String index;
    String name;
    String type;
    String rating;
    String points;
    ArrayList<String> tags;

    public Problem() {}

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getContestId() {
        return contestId;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRating() {
        return rating;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getPoints() {
        return points;
    }

    public String toString() {
        return "contestId: " + contestId + ", index: " + index + ", name: " + name + ", type: " + type + ", rating: " + rating + ", tags: " + tags;
    }

}