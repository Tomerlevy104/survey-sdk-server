package com.surveysdk.server.services;

import com.surveysdk.server.models.Question;
import com.surveysdk.server.repositories.SurveyRepository;
import com.surveysdk.server.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import com.surveysdk.server.db.SurveyDocument;
import com.surveysdk.server.models.Survey;
import com.surveysdk.server.utils.converters.SurveyConverter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    // Create survey
    public Survey saveSurvey(Survey survey) {
        generateQuestionId(survey.getQuestions());
        SurveyDocument doc = SurveyConverter.fromSurveyModelToSurveyDocument(survey);
        SurveyDocument saved = surveyRepository.save(doc);
        return SurveyConverter.fromSurveyDocumentToSurveyModel(saved);
    }

    // Get all surveys
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll()
                .stream()
                .map(SurveyConverter::fromSurveyDocumentToSurveyModel)
                .toList();
    }

    // Get survey by ID
    public Survey getSurveyById(String id) {
        SurveyDocument doc = surveyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + id));

        return SurveyConverter.fromSurveyDocumentToSurveyModel(doc);
    }

    // Delete survey
    public void deleteSurveyById(String id) {
        if (!surveyRepository.existsById(id)) {
            throw new NotFoundException("Survey not found: " + id);
        }
        surveyRepository.deleteById(id);
    }

    // Update survey
    public Survey updateSurvey(String id, Survey updatedSurvey) {
        SurveyDocument existing = surveyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + id));

        generateQuestionId(updatedSurvey.getQuestions());

        // Update only editable fields
        existing.setTitle(updatedSurvey.getTitle());
        existing.setQuestions(updatedSurvey.getQuestions());

        SurveyDocument saved = surveyRepository.save(existing);
        return SurveyConverter.fromSurveyDocumentToSurveyModel(saved);
    }

    // Get random survey
    public Survey getRandomSurvey() {
        List<SurveyDocument> allSurveys = surveyRepository.findAll();

        if (allSurveys.isEmpty()) {
            throw new NotFoundException("No surveys found");
        }

        int index = ThreadLocalRandom.current().nextInt(allSurveys.size());
        SurveyDocument picked = allSurveys.get(index);

        return SurveyConverter.fromSurveyDocumentToSurveyModel(picked);
    }

    // Generate ID for questions
    private void generateQuestionId(List<Question> questions) {
        if (questions == null) return;

        for (Question q : questions) {
            if (q == null) continue;

            String id = q.getId();
            if (id == null || id.trim().isEmpty()) {
                q.setId(UUID.randomUUID().toString());
            }
        }
    }


}
