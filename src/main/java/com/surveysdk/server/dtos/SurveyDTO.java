package com.surveysdk.server.dtos;

import java.util.List;

public class SurveyDTO {

    private String id;
    private String title;
    private List<QuestionDTO> questions;

    public SurveyDTO() { }

    public SurveyDTO(String id, String title, List<QuestionDTO> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
