package com.surveysdk.server.repositories;

import com.surveysdk.server.entities.SurveyResponseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyResponseRepository extends MongoRepository<SurveyResponseEntity, String> {
    List<SurveyResponseEntity> findBySurveyIdAndDeveloperId(String surveyId, String developerId);
}
