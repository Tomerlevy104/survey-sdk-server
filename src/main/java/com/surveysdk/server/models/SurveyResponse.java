package com.surveysdk.server.models;

import java.util.List;

public class SurveyResponse {

    private String surveyId;
    private List<Answer> answers;

    public SurveyResponse() {}

    public SurveyResponse(String surveyId, List<Answer> answers) {
        this.surveyId = surveyId;
        this.answers = answers;
    }

    public String getSurveyId() { return surveyId; }
    public void setSurveyId(String surveyId) { this.surveyId = surveyId; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}
