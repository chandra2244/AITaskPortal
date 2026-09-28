package backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String aiSummary;

    private String aiCategory;

    private String aiComplexity;

    private Integer estimatedHours;

    @Column(columnDefinition = "TEXT")
    private String aiReason;


    // =========================
    // ID
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // =========================
    // TITLE
    // =========================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // =========================
    // DESCRIPTION
    // =========================

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // =========================
    // PRIORITY
    // =========================

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    // =========================
    // STATUS
    // =========================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // =========================
    // AI SUMMARY
    // =========================

    public String getAiSummary() {
        return aiSummary;
    }

    public void setAiSummary(String aiSummary) {
        this.aiSummary = aiSummary;
    }


    // =========================
    // AI CATEGORY
    // =========================

    public String getAiCategory() {
        return aiCategory;
    }

    public void setAiCategory(String aiCategory) {
        this.aiCategory = aiCategory;
    }


    // =========================
    // AI COMPLEXITY
    // =========================

    public String getAiComplexity() {
        return aiComplexity;
    }

    public void setAiComplexity(String aiComplexity) {
        this.aiComplexity = aiComplexity;
    }


    // =========================
    // ESTIMATED HOURS
    // =========================

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }


    // =========================
    // AI REASON
    // =========================

    public String getAiReason() {
        return aiReason;
    }

    public void setAiReason(String aiReason) {
        this.aiReason = aiReason;
    }
}