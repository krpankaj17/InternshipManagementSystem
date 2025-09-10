package com.internshipManagement.ongc.Services;

import com.internshipManagement.ongc.DTO.EvaluationRecordDTO;
import com.internshipManagement.ongc.Model.EvaluationRecord;
import com.internshipManagement.ongc.Model.UserAccount;
import com.internshipManagement.ongc.Model.Interns;
import com.internshipManagement.ongc.mapper.EvaluationRecordMapper;
import com.internshipManagement.ongc.repository.EvaluationRecordRepository;
import com.internshipManagement.ongc.repository.UserAccountRepository;
import com.internshipManagement.ongc.repository.InternRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EvaluationRecordServiceImpl implements EvaluationRecordService {

    @Autowired
    private EvaluationRecordRepository repository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public EvaluationRecordDTO createEvaluation(EvaluationRecordDTO dto) {
        UserAccount mentor = userAccountRepository.findById(dto.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        if (!"MENTOR".equalsIgnoreCase(mentor.getRole())) {
            throw new RuntimeException("User is not a mentor.");
        }

        Interns intern = internRepository.findById(dto.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        if (dto.getEvaluationType() == null ||
                (!dto.getEvaluationType().equalsIgnoreCase("DAILY") &&
                        !dto.getEvaluationType().equalsIgnoreCase("FINAL"))) {
            throw new RuntimeException("evaluationType must be 'DAILY' or 'FINAL'.");
        }

        EvaluationRecord record = EvaluationRecordMapper.toEntity(dto);
        record.setScore(calculateAverageScore(record));

        EvaluationRecord saved = repository.save(record);
        return EvaluationRecordMapper.toDTO(saved);
    }

    @Override
    public List<EvaluationRecordDTO> getAllEvaluations() {
        return repository.findAll().stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationRecordDTO getEvaluationById(String id) {
        return repository.findById(id)
                .map(EvaluationRecordMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    @Override
    public List<EvaluationRecordDTO> getEvaluationsByInternId(String internId) {
        return repository.findByInternId(internId).stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationRecordDTO> getEvaluationsByMentorId(String mentorId) {
        return repository.findByMentorId(mentorId).stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationRecordDTO> getEvaluationsByType(String evaluationType) {
        return repository.findByEvaluationType(evaluationType.toUpperCase()).stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationRecordDTO> getEvaluationsByTypeAndInternId(String evaluationType, String internId) {
        return repository.findByEvaluationTypeAndInternId(evaluationType.toUpperCase(), internId).stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluationRecordDTO> getEvaluationsByTypeAndMentorId(String evaluationType, String mentorId) {
        return repository.findByEvaluationTypeAndMentorId(evaluationType.toUpperCase(), mentorId).stream()
                .map(EvaluationRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationRecordDTO updateEvaluation(String id, EvaluationRecordDTO dto) {
        EvaluationRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        existing.setFeedback(dto.getFeedback());
        existing.setEvaluationDate(dto.getEvaluationDate());
        existing.setStatus(dto.getStatus());
        existing.setTechnicalScore(dto.getTechnicalScore());
        existing.setCommunicationScore(dto.getCommunicationScore());
        existing.setTeamworkScore(dto.getTeamworkScore());
        existing.setPunctualityScore(dto.getPunctualityScore());
        existing.setInitiativeScore(dto.getInitiativeScore());

        if (dto.getEvaluationType() != null) {
            if (!dto.getEvaluationType().equalsIgnoreCase("DAILY") &&
                    !dto.getEvaluationType().equalsIgnoreCase("FINAL")) {
                throw new RuntimeException("evaluationType must be 'DAILY' or 'FINAL'.");
            }
            existing.setEvaluationType(dto.getEvaluationType().toUpperCase());
        }

        existing.setScore(calculateAverageScore(existing));

        EvaluationRecord updated = repository.save(existing);
        return EvaluationRecordMapper.toDTO(updated);
    }

    @Override
    public void deleteEvaluation(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Evaluation not found");
        }
        repository.deleteById(id);
    }

    private BigDecimal calculateAverageScore(EvaluationRecord record) {
        BigDecimal total = BigDecimal.ZERO;
        int count = 0;

        if (record.getTechnicalScore() != null) {
            total = total.add(record.getTechnicalScore());
            count++;
        }
        if (record.getCommunicationScore() != null) {
            total = total.add(record.getCommunicationScore());
            count++;
        }
        if (record.getTeamworkScore() != null) {
            total = total.add(record.getTeamworkScore());
            count++;
        }
        if (record.getPunctualityScore() != null) {
            total = total.add(record.getPunctualityScore());
            count++;
        }
        if (record.getInitiativeScore() != null) {
            total = total.add(record.getInitiativeScore());
            count++;
        }

        if (count == 0) {
            return BigDecimal.ZERO;
        } else {
            return total.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
        }
    }
}
