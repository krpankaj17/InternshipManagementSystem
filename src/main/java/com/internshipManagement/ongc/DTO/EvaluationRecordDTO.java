package com.internshipManagement.ongc.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EvaluationRecordDTO {

    private String id;
    private String internId;
    private String mentorId;

    private BigDecimal score; // Overall Score

    private String feedback;
    private LocalDate evaluationDate;
    private String status;

    // Per-category scores
    private BigDecimal technicalScore;
    private BigDecimal communicationScore;
    private BigDecimal teamworkScore;
    private BigDecimal punctualityScore;
    private BigDecimal initiativeScore;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String evaluationType;

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
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
}
