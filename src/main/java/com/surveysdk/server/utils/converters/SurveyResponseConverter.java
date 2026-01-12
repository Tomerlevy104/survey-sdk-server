package com.surveysdk.server.utils.converters;

import com.surveysdk.server.db.SurveyResponseDocument;
import com.surveysdk.server.models.SurveyResponse;

public class SurveyResponseConverter {

    private SurveyResponseConverter() {
        // utility class
    }

    public static SurveyResponseDocument fromModelToDocument(SurveyResponse model) {
        if (model == null) return null;

        SurveyResponseDocument doc = new SurveyResponseDocument();
        doc.setSurveyId(model.getSurveyId());
        doc.setAnswers(model.getAnswers());
        return doc;
    }

    public static SurveyResponse fromDocumentToModel(SurveyResponseDocument doc) {
        if (doc == null) return null;

        SurveyResponse model = new SurveyResponse();
        model.setSurveyId(doc.getSurveyId());
        model.setAnswers(doc.getAnswers());
        return model;
    }
}
