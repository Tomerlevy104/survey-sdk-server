package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.SurveyDTO;
import com.surveysdk.server.dtos.SurveyResponseDTO;
import com.surveysdk.server.services.AdminSurveyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/surveys")
@SecurityRequirement(name = "bearerAuth")
public class AdminSurveyController {

    private final AdminSurveyService adminSurveyService;

    public AdminSurveyController(AdminSurveyService adminSurveyService) {
        this.adminSurveyService = adminSurveyService;
    }

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDTO saveSurvey(
            @RequestBody SurveyDTO survey,
            @RequestAttribute("developerId") String developerId
    ) {
        if (survey.getId() != null && survey.getId().trim().isEmpty()) {
            survey.setId(null);
        }
        System.out.println("In AdminSurveyController.saveSurvey");
        System.out.println("developerId = " + developerId);
        System.out.println("survey title = " + survey.getTitle());
        return adminSurveyService.saveSurvey(survey, developerId);
    }

    // Get all surveys (for this developer)
    @GetMapping
    public List<SurveyDTO> getAllSurveysForDeveloper(
            @RequestAttribute("developerId") String developerId
    ) {
        return adminSurveyService.getAllSurveysForDeveloper(developerId);
    }

    // Get survey by id
    @GetMapping("/{id}")
    public SurveyDTO getSurveyByIdForDeveloper(
            @PathVariable String id,
            @RequestAttribute("developerId") String developerId
    ) {
        return adminSurveyService.getSurveyByIdForDeveloper(id, developerId);
    }

    // Update survey by id (for this developer)
    @PutMapping("/{id}")
    public SurveyDTO updateSurveyForDeveloper(
            @PathVariable String id,
            @RequestBody SurveyDTO survey,
            @RequestAttribute("developerId") String developerId
    ) {
        return adminSurveyService.updateSurveyForDeveloper(id, survey, developerId);
    }

    // Delete survey by id (for this developer)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSurveyByIdAndDeveloper(
            @PathVariable String id,
            @RequestAttribute("developerId") String developerId
    ) {
        adminSurveyService.deleteSurveyByIdAndDeveloper(id, developerId);
    }

    // Get all response for specific survey
    @GetMapping("/{surveyId}/responses")
    public List<SurveyResponseDTO> getAllResponsesOfSurvey(
            @PathVariable String surveyId,
            @RequestAttribute("developerId") String developerId
    ) {
        return adminSurveyService.getAllResponseOfSurvey(surveyId, developerId);
    }
}
