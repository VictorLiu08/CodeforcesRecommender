package com.example.cfrecommendation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    String contestId;
    String participantType;
    String ghost;
    String startTimeSeconds;

    public Author() {
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public void setGhost(String ghost) {
        this.ghost = ghost;
    }

    public void setStartTimeSeconds(String startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    public String getContestId() {
        return this.contestId;
    }

    public String getParticipantType() {
        return this.participantType;
    }

    public String getGhost() {
        return this.ghost;
    }

    public String getStartTimeSeconds() {
        return this.startTimeSeconds;
    }

    public String toString() {
        return "contestId: " + contestId + ", participantType: " + participantType + ", ghost: " + ghost + ", startTimeSeconds: " + startTimeSeconds;
    }

}