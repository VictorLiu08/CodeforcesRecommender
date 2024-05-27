package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemStatistics {

    public int contestId;
    public String index;
    @JsonProperty("solvedCount") // Ensure the field matches the JSON property name
    public int solvedCount;

    public ProblemStatistics() {
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setSolvedCount(int solvedCount) {
        this.solvedCount = solvedCount;
    }

    public int getContestId() {
        return this.contestId;
    }

    public String getIndex() {
        return this.index;
    }

    public int getSolvedCount() {
        return this.solvedCount;
    }

}
