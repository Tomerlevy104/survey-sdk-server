package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.SurveyDTO;
import com.surveysdk.server.services.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

//    @GetMapping("/random")
//    public Survey getRandomSurvey() {
//        return surveyService.getRandomSurvey();
//    }
    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDTO createSurvey(@RequestBody SurveyDTO survey) {
        // Set SurveyId as null if the id is ""
        if (survey.getId() != null && survey.getId().trim().isEmpty()) {
            survey.setId(null);
        }
        return surveyService.saveSurvey(survey);
    }

    // Get all surveys
    @GetMapping
    public List<SurveyDTO> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    // Get survey by id
    @GetMapping("/{id}")
    public SurveyDTO getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    // Delete survey by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurveyById(id);
    }

    // Update survey by id
    @PutMapping("/{id}")
    public SurveyDTO updateSurvey(@PathVariable String id, @RequestBody SurveyDTO survey) {
        return surveyService.updateSurvey(id, survey);
    }

    // Get random survey
    @GetMapping("/random")
    public SurveyDTO getRandomSurvey() {
        return surveyService.getRandomSurvey();
    }
}
