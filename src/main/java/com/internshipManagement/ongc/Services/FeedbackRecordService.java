package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.FeedbackRecordDTO;

import java.util.List;

public interface FeedbackRecordService {
    FeedbackRecordDTO createFeedback(FeedbackRecordDTO dto);
    List<FeedbackRecordDTO> getAllFeedbacks();
    FeedbackRecordDTO getFeedbackById(String id);
    List<FeedbackRecordDTO> getFeedbacksByInternId(String internId);
    List<FeedbackRecordDTO> getFeedbacksByMentorId(String mentorId);
    FeedbackRecordDTO updateFeedback(String id, FeedbackRecordDTO dto);
    void deleteFeedback(String id);
}
