package com.surveysdk.server.dtos;

import java.util.List;

public class SurveyResponseDTO {

    private String surveyId;
    private List<AnswerDTO> answers;

    public SurveyResponseDTO() {
    }

    public SurveyResponseDTO(String surveyId, List<AnswerDTO> answers) {
        this.surveyId = surveyId;
        this.answers = answers;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
