package com.surveysdk.server.repositories;

import com.surveysdk.server.db.SurveyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<SurveyDocument, String> {
    // no code needed here for basic CRUD
}
