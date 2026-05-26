package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.dto.TaskRequest;
import backend.entity.Task;
import backend.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request) {

        Task task = new Task();

        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setStatus(request.getStatus());

        String summary = "AI Suggestion: ";

        if (request.getDescription().toLowerCase().contains("urgent")) {

            summary += "High Priority Task";

            task.setPriority("HIGH");

        } else if (request.getDescription().length() > 100) {

            summary += "Complex Task";

            task.setPriority("MEDIUM");

        } else {

            summary += "Simple Task";

            task.setPriority("LOW");
        }

        task.setAiSummary(summary);

        taskRepository.save(task);

        return ResponseEntity.ok("Task Created Successfully");
    }

    @GetMapping
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return ResponseEntity.ok("Task Deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow();

        task.setStatus(request.getStatus());

        taskRepository.save(task);

        return ResponseEntity.ok("Task Updated Successfully");
    }
}