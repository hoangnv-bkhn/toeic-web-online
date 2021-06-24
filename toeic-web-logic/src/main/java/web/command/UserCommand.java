package web.command;

import web.core.dto.UserDTO;
import web.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand(){
        this.pojo = new UserDTO();
    }
}
