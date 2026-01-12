package com.surveysdk.server.db;

import com.surveysdk.server.models.Answer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "survey_responses")
public class SurveyResponseDocument {

    @Id
    private String id;

    private String surveyId;

    private List<Answer> answers;

    @CreatedDate
    private Date createdDate;

    public SurveyResponseDocument() {}

    public SurveyResponseDocument(String id, String surveyId, List<Answer> answers, Date createdDate) {
        this.id = id;
        this.surveyId = surveyId;
        this.answers = answers;
        this.createdDate = createdDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSurveyId() { return surveyId; }
    public void setSurveyId(String surveyId) { this.surveyId = surveyId; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
