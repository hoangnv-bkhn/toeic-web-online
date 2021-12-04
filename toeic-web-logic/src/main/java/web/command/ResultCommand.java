package web.command;

import web.core.dto.ResultDTO;
import web.core.web.command.AbstractCommand;

public class ResultCommand extends AbstractCommand<ResultDTO> {
    public ResultCommand () {
        this.pojo = new ResultDTO();
    }
}
