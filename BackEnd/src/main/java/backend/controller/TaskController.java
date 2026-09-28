package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.dto.AIAnalysisResponse;
import backend.dto.TaskRequest;
import backend.entity.Task;
import backend.repository.TaskRepository;
import backend.service.AIService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AIService aiService;


    // ==========================================
    // CREATE TASK
    // ==========================================

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request) {

        try {

            // ------------------------------------------
            // 1. Create Task object
            // ------------------------------------------

            Task task = new Task();

            task.setTitle(request.getTitle());

            task.setDescription(request.getDescription());

            task.setStatus(request.getStatus());


            // ------------------------------------------
            // 2. Send task to REAL AI
            // ------------------------------------------

            AIAnalysisResponse aiResult =
                    aiService.analyseTask(
                            request.getTitle(),
                            request.getDescription()
                    );


            // ------------------------------------------
            // 3. Store AI results
            // ------------------------------------------

            task.setPriority(aiResult.getPriority());

            task.setAiCategory(aiResult.getCategory());

            task.setAiComplexity(aiResult.getComplexity());

            task.setEstimatedHours(aiResult.getEstimatedHours());

            task.setAiSummary(aiResult.getSummary());

            task.setAiReason(aiResult.getReason());


            // ------------------------------------------
            // 4. Save task into PostgreSQL
            // ------------------------------------------

            Task savedTask = taskRepository.save(task);


            // ------------------------------------------
            // 5. Return saved task
            // ------------------------------------------

            return ResponseEntity.ok(savedTask);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .internalServerError()
                    .body("Failed to create task: " + e.getMessage());
        }
    }


    // ==========================================
    // GET ALL TASKS
    // ==========================================

    @GetMapping
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }


    // ==========================================
    // DELETE TASK
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return ResponseEntity.ok("Task Deleted Successfully");
    }


    // ==========================================
    // UPDATE TASK STATUS
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));


        task.setStatus(request.getStatus());

        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }
}