package com.surveysdk.server.utils.converters;

import com.surveysdk.server.entities.SurveyResponseEntity;
import com.surveysdk.server.dtos.SurveyResponseDTO;

public class SurveyResponseConverter {

    private SurveyResponseConverter() {
    }

    public static SurveyResponseEntity fromDtoToEntity(SurveyResponseDTO surveyResponseDTO) {
        if (surveyResponseDTO == null) return null;

        SurveyResponseEntity entity = new SurveyResponseEntity();
        entity.setSurveyId(surveyResponseDTO.getSurveyId());
        entity.setAnswers(surveyResponseDTO.getAnswers());
        return entity;
    }

    public static SurveyResponseDTO fromEntityToDto(SurveyResponseEntity entity) {
        if (entity == null) return null;

        SurveyResponseDTO dto = new SurveyResponseDTO();
        dto.setSurveyId(entity.getSurveyId());
        dto.setAnswers(entity.getAnswers());
        return dto;
    }
}
