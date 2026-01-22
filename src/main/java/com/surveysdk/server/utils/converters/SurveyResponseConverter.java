package com.surveysdk.server.utils.converters;

import com.surveysdk.server.dtos.AnswerDTO;
import com.surveysdk.server.entities.SurveyResponseEntity;
import com.surveysdk.server.dtos.SurveyResponseDTO;

import java.util.List;
import java.util.Map;

public class SurveyResponseConverter {

    private SurveyResponseConverter() {
    }

    // From DTO to Entity
    public static SurveyResponseEntity fromDtoToEntity(SurveyResponseDTO surveyResponseDTO) {
        if (surveyResponseDTO == null) return null;
        SurveyResponseEntity entity = new SurveyResponseEntity();
        entity.setSurveyId(surveyResponseDTO.getSurveyId());
        entity.setAnswers(surveyResponseDTO.getAnswers());
        return entity;
    }

    // From Entity to DTO
    public static SurveyResponseDTO fromEntityToDto(SurveyResponseEntity entity) {
        if (entity == null) return null;
        SurveyResponseDTO dto = new SurveyResponseDTO();
        dto.setSurveyId(entity.getSurveyId());
        dto.setAnswers(entity.getAnswers());
        return dto;
    }
}
