package com.surveysdk.server.utils.converters;

import com.surveysdk.server.entities.SurveyEntity;
import com.surveysdk.server.dtos.SurveyDTO;

public class SurveyConverter {

    private SurveyConverter() {
    }

    /**
     * Convert SurveyDTO -> SurveyEntity
     */
    public static SurveyEntity fromSurveyDtoToSurveyEntity(SurveyDTO surveyDTO, String developerId) {
        if (surveyDTO == null) {
            return null;
        }

        SurveyEntity entity = new SurveyEntity();
        entity.setId(surveyDTO.getId());
        entity.setDeveloperId(developerId);
        entity.setTitle(surveyDTO.getTitle());
        entity.setQuestions(surveyDTO.getQuestions());
        return entity;
    }
    /**
     * Convert SurveyEntity -> SurveyDTO
     */
    public static SurveyDTO fromSurveyEntityToSurveyDto(SurveyEntity surveyEntity) {
        if (surveyEntity == null) {
            return null;
        }

        SurveyDTO survey = new SurveyDTO();
        survey.setId(surveyEntity.getId());
        survey.setTitle(surveyEntity.getTitle());
        survey.setQuestions(surveyEntity.getQuestions());
        survey.setCreatedDate(surveyEntity.getCreatedDate());
        return survey;
    }
}
