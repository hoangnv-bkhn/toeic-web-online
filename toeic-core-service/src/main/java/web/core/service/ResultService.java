package web.core.service;

import web.core.dto.ExaminationQuestionDTO;
import web.core.dto.ResultDTO;

import java.util.List;

public interface ResultService {
    ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions);
}
