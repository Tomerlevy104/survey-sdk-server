package com.surveysdk.server.entities;

import com.surveysdk.server.dtos.AnswerDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "survey_responses")
public class SurveyResponseEntity {

    @Id
    private String id;

    private String surveyId;

    private List<AnswerDTO> answers;

    @CreatedDate
    private Date createdDate;

    public SurveyResponseEntity() {}

    public SurveyResponseEntity(String id, String surveyId, List<AnswerDTO> answers, Date createdDate) {
        this.id = id;
        this.surveyId = surveyId;
        this.answers = answers;
        this.createdDate = createdDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSurveyId() { return surveyId; }
    public void setSurveyId(String surveyId) { this.surveyId = surveyId; }

    public List<AnswerDTO> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
