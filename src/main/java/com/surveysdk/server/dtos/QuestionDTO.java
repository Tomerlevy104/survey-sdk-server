package com.surveysdk.server.dtos;

import java.util.List;

public class QuestionDTO {

    private String id;
    private QuestionType type;
    private String prompt;
    private List<String> options; // null/empty for TEXT

    public QuestionDTO() { }

    public QuestionDTO(String id, QuestionType type, String prompt, List<String> options) {
        this.id = id;
        this.type = type;
        this.prompt = prompt;
        this.options = options;
    }

    // Getters
    public String getId() {
        return id;
    }

    public QuestionType getType() {
        return type;
    }

    public String getPrompt() {
        return prompt;
    }

    public List<String> getOptions() {
        return options;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
