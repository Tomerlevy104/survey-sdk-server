package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.SurveyResponseDTO;
import com.surveysdk.server.services.SurveyResponseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sdk/survey-responses")
@SecurityRequirement(name = "apiKeyAuth")
public class SdkSurveyResponseController {

    private final SurveyResponseService surveyResponseService;

    public SdkSurveyResponseController(SurveyResponseService surveyResponseService) {
        this.surveyResponseService = surveyResponseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyResponseDTO saveResponse(@RequestBody SurveyResponseDTO responseDto,
                                          @RequestAttribute("developerId") String developerId) {
        return surveyResponseService.saveResponse(responseDto, developerId);
    }
}
