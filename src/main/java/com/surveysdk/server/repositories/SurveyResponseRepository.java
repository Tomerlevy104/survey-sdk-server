package com.surveysdk.server.repositories;

import com.surveysdk.server.db.SurveyResponseDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyResponseRepository extends MongoRepository<SurveyResponseDocument, String> {
    List<SurveyResponseDocument> findBySurveyId(String surveyId);
}
