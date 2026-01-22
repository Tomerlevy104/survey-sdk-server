package com.surveysdk.server.repositories;

import com.surveysdk.server.entities.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends MongoRepository<SurveyEntity, String> {

    List<SurveyEntity> findAllByDeveloperId(String developerId);
    Optional<SurveyEntity> findByIdAndDeveloperId(String id, String developerId);
}
