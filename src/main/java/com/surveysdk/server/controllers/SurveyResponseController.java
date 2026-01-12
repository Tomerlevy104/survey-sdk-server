package com.surveysdk.server.controllers;

import com.surveysdk.server.models.SurveyResponse;
import com.surveysdk.server.services.SurveyResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/survey-responses")
public class SurveyResponseController {

    private final SurveyResponseService surveyResponseService;

    public SurveyResponseController(SurveyResponseService surveyResponseService) {
        this.surveyResponseService = surveyResponseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyResponse submit(@RequestBody SurveyResponse response) {
        return surveyResponseService.saveResponse(response);
    }
}
