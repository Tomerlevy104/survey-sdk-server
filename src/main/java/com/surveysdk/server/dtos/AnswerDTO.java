package com.surveysdk.server.dtos;

public class AnswerDTO {

    private String questionId;
    private String value;

    public AnswerDTO() {}

    public AnswerDTO(String questionId, String value) {
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
