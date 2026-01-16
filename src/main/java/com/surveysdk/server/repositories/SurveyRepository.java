package com.surveysdk.server.repositories;

import com.surveysdk.server.entities.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<SurveyEntity, String> {
    // no code needed here for basic CRUD
}
