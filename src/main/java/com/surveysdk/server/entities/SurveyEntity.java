package com.surveysdk.server.entities;

import com.surveysdk.server.dtos.QuestionDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "surveys")
public class SurveyEntity {

    @Id
    private String id;
    private String developerId;
    private String title;
    private List<QuestionDTO> questions;
    @CreatedDate
    private Date createdDate;

    public SurveyEntity() {}

    public SurveyEntity(String id, String developerId, String title, List<QuestionDTO> questions, Date createdDate) {
        this.id = id;
        this.developerId = developerId;
        this.title = title;
        this.questions = questions;
        this.createdDate = createdDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDeveloperId() { return developerId; }
    public void setDeveloperId(String developerId) { this.developerId = developerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
