package web.controller.admin;

import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.log4j.Logger;
import web.command.UserCommand;
import web.core.dto.RoleDTO;
import web.core.dto.UserDTO;
import web.core.service.RoleService;
import web.core.service.UserService;
import web.core.service.impl.RoleServiceImpl;
import web.core.service.impl.UserServiceImpl;
import web.core.web.utils.WebCommonUtil;
import web.core.web.common.WebConstant;
import web.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html"})
public class UserController extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    UserService userService = new UserServiceImpl();
    RoleService roleService = new RoleServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> mapProperties = new HashMap<String, Object>();
            Object[] objects = userService.findByProperty(mapProperties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            if (command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapRedirectMessage(bundle);
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (pojo != null && pojo.getUserId() != null) {
                try {
                    command.setPojo(userService.findById(pojo.getUserId()));
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
            command.setRoles(roleService.findAll());
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(request, response);
        }
    }

    private Map<String, String> buildMapRedirectMessage(ResourceBundle bundle) {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.error"));
        return mapMessage;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserCommand command = FormUtil.populate(UserCommand.class, request);
            UserDTO pojo = command.getPojo();
            if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());
                    pojo.setRoleDTO(roleDTO);
                    if (pojo != null && pojo.getUserId() != null) {
                        userService.updateUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                    } else {
                        userService.saveUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
            log.error(e.getMessage(), e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
        rd.forward(request, response);
    }
}
