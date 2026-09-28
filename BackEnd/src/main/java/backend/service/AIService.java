package backend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import backend.dto.AIAnalysisResponse;
import backend.dto.GroqRequest;
import backend.dto.GroqResponse;

@Service
public class AIService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url}")
    private String apiUrl;

    @Value("${groq.model}")
    private String model;

    private final RestClient restClient = RestClient.create();

    private final ObjectMapper mapper = new ObjectMapper();

    public AIAnalysisResponse analyseTask(String title, String description) {

        try {

            String prompt = """
                    You are an AI Task Management Assistant.

                    Analyze the following task.

                    Title:
                    %s

                    Description:
                    %s

                    Return ONLY valid JSON.

                    Do not use markdown.
                    Do not use ```json.
                    Do not explain anything.

                    Example:

                    {
                      "priority":"HIGH",
                      "category":"SECURITY",
                      "complexity":"MEDIUM",
                      "estimatedHours":5,
                      "summary":"Fix JWT token expiration issue.",
                      "reason":"Authentication failure prevents users from logging in."
                    }
                    """.formatted(title, description);

            GroqRequest request = new GroqRequest(
                    model,
                    List.of(
                            new GroqRequest.Message(
                                    "user",
                                    prompt
                            )
                    )
            );

            GroqResponse response = restClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(GroqResponse.class);

            String aiResponse = response.getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();

            System.out.println("========== AI RESPONSE ==========");
            System.out.println(aiResponse);
            System.out.println("================================");

            return mapper.readValue(aiResponse, AIAnalysisResponse.class);
        } catch (Exception e) {

            e.printStackTrace();

            AIAnalysisResponse error = new AIAnalysisResponse();

            error.setPriority("UNKNOWN");
            error.setCategory("UNKNOWN");
            error.setComplexity("UNKNOWN");
            error.setEstimatedHours(0);
            error.setSummary("AI could not analyse the task.");
            error.setReason(e.getMessage());

            return error;
        }
    }
}