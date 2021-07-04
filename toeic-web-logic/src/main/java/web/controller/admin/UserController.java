package web.controller.admin;

import javassist.tools.rmi.ObjectNotFoundException;
import web.command.UserCommand;
import web.core.dto.UserDTO;
import web.core.service.RoleService;
import web.core.service.UserService;
import web.core.service.impl.RoleServiceImpl;
import web.core.service.impl.UserServiceImpl;
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

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html"})
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    RoleService roleService = new RoleServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();

        if (command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> mapProperties = new HashMap<String, Object>();
            Object[] objects = userService.findByProperty(mapProperties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
