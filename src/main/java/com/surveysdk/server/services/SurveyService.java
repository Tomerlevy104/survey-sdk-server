package com.surveysdk.server.services;

import com.surveysdk.server.dtos.QuestionDTO;
import com.surveysdk.server.repositories.SurveyRepository;
import com.surveysdk.server.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import com.surveysdk.server.entities.SurveyEntity;
import com.surveysdk.server.dtos.SurveyDTO;
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
    public SurveyDTO saveSurvey(SurveyDTO survey) {
        generateQuestionId(survey.getQuestions());
        SurveyEntity surveyEntity = SurveyConverter.fromSurveyDtoToSurveyEntity(survey);
        SurveyEntity saved = surveyRepository.save(surveyEntity);
        return SurveyConverter.fromSurveyEntityToSurveyDto(saved);
    }

    // Get all surveys
    public List<SurveyDTO> getAllSurveys() {
        return surveyRepository.findAll()
                .stream()
                .map(SurveyConverter::fromSurveyEntityToSurveyDto)
                .toList();
    }

    // Get survey by ID
    public SurveyDTO getSurveyById(String id) {
        SurveyEntity doc = surveyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + id));

        return SurveyConverter.fromSurveyEntityToSurveyDto(doc);
    }

    // Delete survey
    public void deleteSurveyById(String id) {
        if (!surveyRepository.existsById(id)) {
            throw new NotFoundException("Survey not found: " + id);
        }
        surveyRepository.deleteById(id);
    }

    // Update survey
    public SurveyDTO updateSurvey(String id, SurveyDTO updatedSurvey) {
        SurveyEntity existing = surveyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + id));

        generateQuestionId(updatedSurvey.getQuestions());

        // Update only editable fields
        existing.setTitle(updatedSurvey.getTitle());
        existing.setQuestions(updatedSurvey.getQuestions());

        SurveyEntity saved = surveyRepository.save(existing);
        return SurveyConverter.fromSurveyEntityToSurveyDto(saved);
    }

    // Get random survey
    public SurveyDTO getRandomSurvey() {
        List<SurveyEntity> allSurveys = surveyRepository.findAll();

        if (allSurveys.isEmpty()) {
            throw new NotFoundException("No surveys found");
        }

        int index = ThreadLocalRandom.current().nextInt(allSurveys.size());
        SurveyEntity picked = allSurveys.get(index);

        return SurveyConverter.fromSurveyEntityToSurveyDto(picked);
    }

    // Generate ID for questions
    private void generateQuestionId(List<QuestionDTO> questions) {
        if (questions == null) return;

        for (QuestionDTO q : questions) {
            if (q == null) continue;

            String id = q.getId();
            if (id == null || id.trim().isEmpty()) {
                q.setId(UUID.randomUUID().toString());
            }
        }
    }


}
