package com.internshipManagement.ongc.Model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EvaluationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "intern_id", nullable = false)
    private String internId;

    @Column(name = "mentor_id", nullable = false)
    private String mentorId;

    @Column(precision = 5, scale = 2)
    private BigDecimal score;

    @Column(length = 500)
    private String feedback;

    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;

    @Column(length = 20)
    private String status;

    @Column(name = "technical_score", precision = 3, scale = 1)
    private BigDecimal technicalScore;

    @Column(name = "communication_score", precision = 3, scale = 1)
    private BigDecimal communicationScore;

    @Column(name = "teamwork_score", precision = 3, scale = 1)
    private BigDecimal teamworkScore;

    @Column(name = "punctuality_score", precision = 3, scale = 1)
    private BigDecimal punctualityScore;

    @Column(name = "initiative_score", precision = 3, scale = 1)
    private BigDecimal initiativeScore;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "evaluation_type", length = 20, nullable = false)
    private String evaluationType;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(BigDecimal technicalScore) {
        this.technicalScore = technicalScore;
    }

    public BigDecimal getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(BigDecimal communicationScore) {
        this.communicationScore = communicationScore;
    }

    public BigDecimal getTeamworkScore() {
        return teamworkScore;
    }

    public void setTeamworkScore(BigDecimal teamworkScore) {
        this.teamworkScore = teamworkScore;
    }

    public BigDecimal getPunctualityScore() {
        return punctualityScore;
    }

    public void setPunctualityScore(BigDecimal punctualityScore) {
        this.punctualityScore = punctualityScore;
    }

    public BigDecimal getInitiativeScore() {
        return initiativeScore;
    }

    public void setInitiativeScore(BigDecimal initiativeScore) {
        this.initiativeScore = initiativeScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }
}
