package web.command;

import web.core.dto.ExerciseDTO;
import web.core.web.command.AbstractCommand;

public class ExerciseCommand extends AbstractCommand<ExerciseDTO> {
    public ExerciseCommand() {
        this.pojo = new ExerciseDTO();
    }

}
