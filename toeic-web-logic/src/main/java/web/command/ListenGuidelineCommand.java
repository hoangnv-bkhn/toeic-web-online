package web.command;

import web.core.dto.ListenGuidelineDTO;
import web.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand(){
        this.pojo = new ListenGuidelineDTO();
    }
}
