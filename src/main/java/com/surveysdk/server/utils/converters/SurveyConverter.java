package com.surveysdk.server.utils.converters;

import com.surveysdk.server.db.SurveyDocument;
import com.surveysdk.server.models.Survey;

public class SurveyConverter {

    private SurveyConverter() {
    }

    /**
     * Convert SurveyModel -> SurveyDocument (MongoDB document)
     */
    public static SurveyDocument fromSurveyModelToSurveyDocument(Survey survey) {
        if (survey == null) {
            return null;
        }

        SurveyDocument doc = new SurveyDocument();
        doc.setId(survey.getId());
        doc.setTitle(survey.getTitle());
        doc.setQuestions(survey.getQuestions());
        return doc;
    }
    /**
     * Convert SurveyDocument -> SurveyModel
     */
    public static Survey fromSurveyDocumentToSurveyModel(SurveyDocument doc) {
        if (doc == null) {
            return null;
        }

        Survey survey = new Survey();
        survey.setId(doc.getId());
        survey.setTitle(doc.getTitle());
        survey.setQuestions(doc.getQuestions());
        return survey;
    }
}
