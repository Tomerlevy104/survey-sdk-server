package com.surveysdk.server.utils.converters;

import com.surveysdk.server.entities.SurveyEntity;
import com.surveysdk.server.dtos.SurveyDTO;

public class SurveyConverter {

    private SurveyConverter() {
    }

    /**
     * Convert SurveyDTO -> SurveyEntity
     */
    public static SurveyEntity fromSurveyDtoToSurveyEntity(SurveyDTO surveyDTO) {
        if (surveyDTO == null) {
            return null;
        }

        SurveyEntity doc = new SurveyEntity();
        doc.setId(surveyDTO.getId());
        doc.setTitle(surveyDTO.getTitle());
        doc.setQuestions(surveyDTO.getQuestions());
        return doc;
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
        return survey;
    }
}
