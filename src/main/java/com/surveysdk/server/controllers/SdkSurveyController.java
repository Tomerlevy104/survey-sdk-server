package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.SurveyDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import com.surveysdk.server.services.SdkSurveyService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sdk/surveys")
@SecurityRequirement(name = "apiKeyAuth")
public class SdkSurveyController {

    private final SdkSurveyService sdkSurveyService;

    public SdkSurveyController(SdkSurveyService sdkSurveyService) {
        this.sdkSurveyService = sdkSurveyService;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Get survey by id for developer
    @GetMapping("/{id}")
    public SurveyDTO getSurveyByIdForDeveloper(
            @PathVariable String id,
            @RequestAttribute("developerId") String developerId
    ) {
        return sdkSurveyService.getSurveyByIdForDeveloper(id, developerId);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Get random survey
    @GetMapping("/random")
    public SurveyDTO getRandomSurvey(@RequestAttribute("developerId") String developerId) {
        return sdkSurveyService.getRandomSurveyForDeveloper(developerId);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
}