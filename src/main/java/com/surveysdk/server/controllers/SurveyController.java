package com.surveysdk.server.controllers;

import com.surveysdk.server.models.Survey;
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
    public Survey createSurvey(@RequestBody Survey survey) {
        // Set SurveyId as null if the id is ""
        if (survey.getId() != null && survey.getId().trim().isEmpty()) {
            survey.setId(null);
        }
        return surveyService.saveSurvey(survey);
    }

    // Get all
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    // Get by id
    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    // Delete by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurveyById(id);
    }

    // Update survey by id
    @PutMapping("/{id}")
    public Survey updateSurvey(@PathVariable String id, @RequestBody Survey survey) {
        return surveyService.updateSurvey(id, survey);
    }

    // Get random survey
    @GetMapping("/random")
    public Survey getRandomSurvey() {
        return surveyService.getRandomSurvey();
    }
}
