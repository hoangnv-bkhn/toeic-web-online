package web.command;

import web.core.dto.ExaminationQuestionDTO;
import web.core.web.command.AbstractCommand;

public class ExaminationQuestionCommand extends AbstractCommand<ExaminationQuestionDTO> {
    public ExaminationQuestionCommand() {
        this.pojo = new ExaminationQuestionDTO();
    }
    private Integer examinationId;
    private Integer listenScore;
    private Integer readingScore;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getListenScore() {
        return listenScore;
    }

    public void setListenScore(Integer listenScore) {
        this.listenScore = listenScore;
    }

    public Integer getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(Integer readingScore) {
        this.readingScore = readingScore;
    }
}
