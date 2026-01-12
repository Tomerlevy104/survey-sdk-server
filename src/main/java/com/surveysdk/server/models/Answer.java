package com.surveysdk.server.models;

public class Answer {

    private String questionId;
    private String value;

    public Answer() {}

    public Answer(String questionId, String value) {
        this.questionId = questionId;
        this.value = value;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getValue() {
        return value;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
