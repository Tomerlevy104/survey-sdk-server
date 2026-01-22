package com.surveysdk.server.services;

import com.surveysdk.server.dtos.SurveyDTO;
import com.surveysdk.server.entities.SurveyEntity;
import com.surveysdk.server.repositories.SurveyRepository;
import com.surveysdk.server.utils.converters.SurveyConverter;
import com.surveysdk.server.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SdkSurveyService {
    private final SurveyRepository surveyRepository;

    public SdkSurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // Get a specific survey for a developer
    public SurveyDTO getSurveyByIdForDeveloper(String surveyId, String developerId) {
        SurveyEntity entity = surveyRepository
                .findByIdAndDeveloperId(surveyId, developerId)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + surveyId));
        return SurveyConverter.fromSurveyEntityToSurveyDto(entity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // Get random survey
    public SurveyDTO getRandomSurveyForDeveloper(String developerId) {
        List<SurveyEntity> allSurveys = surveyRepository.findAllByDeveloperId(developerId);
        if (allSurveys.isEmpty()) {
            throw new NotFoundException("No surveys found");
        }
        int index = ThreadLocalRandom.current().nextInt(allSurveys.size());
        SurveyEntity picked = allSurveys.get(index);
        return SurveyConverter.fromSurveyEntityToSurveyDto(picked);
    }
    ///////////////////////////////////////////////////////////////////////////////////////
}
