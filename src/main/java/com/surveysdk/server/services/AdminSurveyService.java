package com.surveysdk.server.services;

import com.surveysdk.server.dtos.QuestionDTO;
import com.surveysdk.server.dtos.SurveyResponseDTO;
import com.surveysdk.server.repositories.SurveyRepository;
import com.surveysdk.server.repositories.SurveyResponseRepository;
import com.surveysdk.server.utils.converters.SurveyResponseConverter;
import com.surveysdk.server.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import com.surveysdk.server.entities.SurveyEntity;
import com.surveysdk.server.dtos.SurveyDTO;
import com.surveysdk.server.utils.converters.SurveyConverter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AdminSurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyResponseRepository surveyResponseRepository;

    public AdminSurveyService(SurveyRepository surveyRepository, SurveyResponseRepository surveyResponseRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyResponseRepository = surveyResponseRepository;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Create survey
    public SurveyDTO saveSurvey(SurveyDTO survey, String developerId) {
        generateQuestionId(survey.getQuestions());
        SurveyEntity surveyEntity = SurveyConverter.fromSurveyDtoToSurveyEntity(survey, developerId);
        SurveyEntity saved = surveyRepository.save(surveyEntity);
        return SurveyConverter.fromSurveyEntityToSurveyDto(saved);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Get all surveys for a developer
    public List<SurveyDTO> getAllSurveysForDeveloper(String developerId) {
        return surveyRepository.findAllByDeveloperId(developerId)
                .stream()
                .map(SurveyConverter::fromSurveyEntityToSurveyDto)
                .toList();
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Get a specific survey for a developer
    public SurveyDTO getSurveyByIdForDeveloper(String surveyId, String developerId) {
        SurveyEntity entity = surveyRepository
                .findByIdAndDeveloperId(surveyId, developerId)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + surveyId));
        return SurveyConverter.fromSurveyEntityToSurveyDto(entity);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Delete survey by id for developer
    public void deleteSurveyByIdAndDeveloper(String surveyId, String developerId) {
        SurveyEntity existing = surveyRepository.findByIdAndDeveloperId(surveyId, developerId)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + surveyId));
        surveyRepository.delete(existing);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Update survey
    public SurveyDTO updateSurveyForDeveloper(String surveyId, SurveyDTO updatedSurvey, String developerId) {
        SurveyEntity existing = surveyRepository.findByIdAndDeveloperId(surveyId, developerId)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + surveyId));

        generateQuestionId(updatedSurvey.getQuestions());

        // Update only editable fields
        existing.setTitle(updatedSurvey.getTitle());
        existing.setQuestions(updatedSurvey.getQuestions());

        SurveyEntity saved = surveyRepository.save(existing);
        return SurveyConverter.fromSurveyEntityToSurveyDto(saved);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////
    // Get All response of specific survey
    public List<SurveyResponseDTO> getAllResponseOfSurvey(String surveyId, String developerId) {
        surveyRepository.findByIdAndDeveloperId(surveyId, developerId)
                .orElseThrow(() -> new NotFoundException("Survey not found: " + surveyId));

        return surveyResponseRepository
                .findBySurveyIdAndDeveloperId(surveyId, developerId)
                .stream()
                .map(entity -> SurveyResponseConverter.fromEntityToDto(entity))
                .toList();
    }

    ///////////////////////////////////////////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////////////////////////////////////////
}
