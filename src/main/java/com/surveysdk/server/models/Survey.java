package com.surveysdk.server.models;

import java.util.List;

public class Survey {

    private String id;
    private String title;
    private String version;
    private List<Question> questions;

    public Survey() { }

    public Survey(String id, String title, String version, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.version = version;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
