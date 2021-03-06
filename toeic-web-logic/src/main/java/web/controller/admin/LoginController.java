package web.controller.admin;

import org.apache.log4j.Logger;
import web.command.UserCommand;
import web.core.common.utils.SessionUtil;
import web.core.dto.CheckLoginDTO;
import web.core.dto.UserDTO;
import web.core.web.common.WebConstant;
import web.core.web.utils.FormUtil;
import web.core.web.utils.SingletonServiceUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/login.html", "/logout.html"})
public class LoginController extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals(WebConstant.LOGIN)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        } else if (action.equals(WebConstant.LOGOUT)) {
            SessionUtil.getInstance().remove(request, WebConstant.LOGIN_NAME);
//            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
//            rd.forward(request, response);
            request.getSession().invalidate();
            response.sendRedirect("/views/web/home.jsp");
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();

        if (pojo != null) {
            CheckLoginDTO login = SingletonServiceUtil.getUserServiceImplInstance().checkLogin(pojo.getName(), pojo.getPassword());
            if (login.isUserExist()) {
                SessionUtil.getInstance().putValue(request, WebConstant.LOGIN_NAME, pojo.getName());
                if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
                    response.sendRedirect("/admin-home.html");
                } else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
                    response.sendRedirect("/home.html");
                }
            } else {
                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));

                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
                rd.forward(request, response);
            }
        }
    }
}
