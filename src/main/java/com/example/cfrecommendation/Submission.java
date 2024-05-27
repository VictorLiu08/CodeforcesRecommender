package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Submission {
    String id;
    String contestId;
    String creationTimeSeconds;
    String relativeTimeSeconds;
    Problem problem;
    Author author;
    String programmingLanguage;
    String verdict;
    String testset;
    String passedTestCount;
    String timeConsumedMillis;
    String memoryConsumedBytes;

    public Submission() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public void setCreationTimeSeconds(String creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public void setRelativeTimeSeconds(String relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public void setTestset(String testset) {
        this.testset = testset;
    }

    public void setPassedTestCount(String passedTestCount) {
        this.passedTestCount = passedTestCount;
    }

    public void setTimeConsumedMillis(String timeConsumedMillis) {
        this.timeConsumedMillis = timeConsumedMillis;
    }

    public void setMemoryConsumedBytes(String memoryConsumedBytes) {
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public String getId() {
        return id;
    }

    public String getContestId() {
        return contestId;
    }

    public String getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public String getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public Problem getProblem() {
        return problem;
    }

    public Author getAuthor() {
        return author;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getVerdict() {
        return verdict;
    }

    public String getTestset() {
        return testset;
    }

    public String getPassedTestCount() {
        return passedTestCount;
    }

    public String getTimeConsumedMillis() {
        return timeConsumedMillis;
    }

    public String getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }

    public String toString() {
        return "id: " + id + ", contestId: " + contestId + ", creationTimeSeconds: " + creationTimeSeconds + ", relativeTimeSeconds: " + relativeTimeSeconds + ", problem: " + problem + ", author: " + author + ", programmingLanguage: " + programmingLanguage + ", verdict: " + verdict + ", testset: " + testset + ", passedTestCount: " + passedTestCount + ", timeConsumedMillis: " + timeConsumedMillis + ", memoryConsumedBytes: " + memoryConsumedBytes + "\n";
    }
}
