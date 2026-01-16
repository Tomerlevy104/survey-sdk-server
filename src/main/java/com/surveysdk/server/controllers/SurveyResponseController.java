package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.SurveyResponseDTO;
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
    public SurveyResponseDTO submit(@RequestBody SurveyResponseDTO responseDto) {
        return surveyResponseService.saveResponse(responseDto);
    }
}
