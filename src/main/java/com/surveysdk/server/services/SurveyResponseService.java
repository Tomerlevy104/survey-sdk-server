package com.surveysdk.server.services;

import com.surveysdk.server.db.SurveyResponseDocument;
import com.surveysdk.server.models.SurveyResponse;
import com.surveysdk.server.repositories.SurveyResponseRepository;
import com.surveysdk.server.utils.converters.SurveyResponseConverter;
import org.springframework.stereotype.Service;

@Service
public class SurveyResponseService {

    private final SurveyResponseRepository surveyResponseRepository;

    public SurveyResponseService(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    public SurveyResponse saveResponse(SurveyResponse response) {
        SurveyResponseDocument doc =
                SurveyResponseConverter.fromModelToDocument(response);

        SurveyResponseDocument saved = surveyResponseRepository.save(doc);
        return SurveyResponseConverter.fromDocumentToModel(saved);
    }
}
