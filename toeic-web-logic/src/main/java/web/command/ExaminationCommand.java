package web.command;

import web.core.dto.ExaminationDTO;
import web.core.web.command.AbstractCommand;

public class ExaminationCommand extends AbstractCommand<ExaminationDTO> {
    public ExaminationCommand() {
        this.pojo = new ExaminationDTO();
    }
}
