package web.core.dto;

import java.io.Serializable;

public class CheckLoginDTO implements Serializable {
    private boolean isUserExist;
    private String roleName;

    public boolean isUserExist() {
        return isUserExist;
    }

    public void setUserExist(boolean userExist) {
        isUserExist = userExist;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
