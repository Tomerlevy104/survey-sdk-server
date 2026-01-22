package com.surveysdk.server.services;

import com.surveysdk.server.entities.SurveyResponseEntity;
import com.surveysdk.server.dtos.SurveyResponseDTO;
import com.surveysdk.server.repositories.SurveyResponseRepository;
import com.surveysdk.server.utils.converters.SurveyResponseConverter;
import org.springframework.stereotype.Service;

@Service
public class SurveyResponseService {

    private final SurveyResponseRepository surveyResponseRepository;

    public SurveyResponseService(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    public SurveyResponseDTO saveResponse(SurveyResponseDTO responseDto, String developerId) {
        SurveyResponseEntity entity =
                SurveyResponseConverter.fromDtoToEntity(responseDto);
        entity.setDeveloperId(developerId);
        SurveyResponseEntity savedEntity = surveyResponseRepository.save(entity);
        return SurveyResponseConverter.fromEntityToDto(savedEntity); // Return surveyResponseDTO
    }
}
