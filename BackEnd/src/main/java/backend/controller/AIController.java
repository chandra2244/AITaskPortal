package backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.dto.AIAnalysisResponse;
import backend.service.AIService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/analyse")
    public AIAnalysisResponse analyse(
            @RequestParam String title,
            @RequestParam String description) {

        return aiService.analyseTask(title, description);
    }
}