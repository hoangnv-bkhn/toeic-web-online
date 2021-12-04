package web.core.service.impl;

import web.core.dto.ExaminationQuestionDTO;
import web.core.dto.ResultDTO;
import web.core.persistence.entity.ExaminationEntity;
import web.core.persistence.entity.ResultEntity;
import web.core.persistence.entity.UserEntity;
import web.core.service.ResultService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.ResultBeanUtil;

import java.sql.Timestamp;
import java.util.List;

public class ResultServiceImpl implements ResultService {
    @Override
    public ResultDTO saveResult(String userName, Integer examinationId, List<ExaminationQuestionDTO> examinationQuestions) {
        ResultDTO result = new ResultDTO();
        if (userName != null && examinationId != null && examinationQuestions != null) {
            UserEntity user = SingletonDaoUtil.getUserDaoInstance().findEqualUnique("name", userName);
            ExaminationEntity examination = SingletonDaoUtil.getExaminationDaoInstance().findById(examinationId);
            ResultEntity entity = new ResultEntity();

            calculateScore(entity, examinationQuestions);
            setDataResultEntity(entity, user, examination);
            entity = SingletonDaoUtil.getResultDaoInstance().save(entity);

            result = ResultBeanUtil.entity2Dto(entity);
        }
        return result;
    }

    private void setDataResultEntity(ResultEntity entity, UserEntity user, ExaminationEntity examination) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setExamination(examination);
        entity.setUser(user);
        entity.setCreatedDate(timestamp);
    }

    private void calculateScore(ResultEntity entity, List<ExaminationQuestionDTO> examinationQuestions) {
        int listenScore = 0;
        int readingScore = 0;
        for (ExaminationQuestionDTO item : examinationQuestions) {
            if (item.getAnswerUser() != null) {
                if (item.getAnswerUser().equals(item.getCorrectAnswer())) {
                    if (item.getNumber() <= 4) {
                        listenScore++;
                    } else {
                        readingScore++;
                    }
                }
            }
        }
        entity.setListenScore(listenScore);
        entity.setReadingScore(readingScore);
    }
}
