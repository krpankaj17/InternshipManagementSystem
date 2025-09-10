package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.FeedbackRecordDTO;
import com.internshipManagement.ongc.Model.FeedbackRecord;
import com.internshipManagement.ongc.mapper.FeedbackRecordMapper;
import com.internshipManagement.ongc.repository.FeedbackRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackRecordServiceImpl implements FeedbackRecordService {

    @Autowired
    private FeedbackRecordRepository repository;

    @Override
    public FeedbackRecordDTO createFeedback(FeedbackRecordDTO dto) {
        FeedbackRecord record = FeedbackRecordMapper.toEntity(dto);
        FeedbackRecord saved = repository.save(record);
        return FeedbackRecordMapper.toDTO(saved);
    }

    @Override
    public List<FeedbackRecordDTO> getAllFeedbacks() {
        return repository.findAll().stream()
                .map(FeedbackRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackRecordDTO getFeedbackById(String id) {
        return repository.findById(id)
                .map(FeedbackRecordMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    @Override
    public List<FeedbackRecordDTO> getFeedbacksByInternId(String internId) {
        return repository.findByInternId(internId).stream()
                .map(FeedbackRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackRecordDTO> getFeedbacksByMentorId(String mentorId) {
        return repository.findByMentorId(mentorId).stream()
                .map(FeedbackRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackRecordDTO updateFeedback(String id, FeedbackRecordDTO dto) {
        FeedbackRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        if (dto.getFeedbackText() != null) existing.setFeedbackText(dto.getFeedbackText());
        if (dto.getFeedbackDate() != null) existing.setFeedbackDate(dto.getFeedbackDate());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());

        FeedbackRecord updated = repository.save(existing);
        return FeedbackRecordMapper.toDTO(updated);
    }

    @Override
    public void deleteFeedback(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Feedback not found");
        }
        repository.deleteById(id);
    }
}
