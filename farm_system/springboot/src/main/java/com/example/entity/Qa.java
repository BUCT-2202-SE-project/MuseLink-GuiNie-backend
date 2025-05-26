package com.example.entity;

public class Qa {
    private Integer qaId;
    private String question;
    private String answer;
    private String askTime;
    private String userId;
    private Integer artifactId;

    public Integer getQaId() {
        return qaId;
    }

    public void setQaId(Integer qaId) {
        this.qaId = qaId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAskTime() {
        return askTime;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }
}
