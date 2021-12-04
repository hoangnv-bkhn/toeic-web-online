package web.controller.web;

import org.apache.commons.lang.StringUtils;
import web.command.ExerciseCommand;
import web.core.dto.ExerciseDTO;
import web.core.web.common.WebConstant;
import web.core.web.utils.FormUtil;
import web.core.web.utils.RequestUtil;
import web.core.web.utils.SingletonServiceUtil;

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

@WebServlet(urlPatterns = {"/danh-sach-bai-tap.html"})
public class ExerciseController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseCommand command = FormUtil.populate(ExerciseCommand.class, request);
        executeSearchExercise(request, command);
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        System.out.println(((ExerciseDTO) command.getPojo()).toString());
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/exercise/list.jsp");
        rd.forward(request, response);

    }

    private void executeSearchExercise(HttpServletRequest request, ExerciseCommand command) {
        Map<String, Object> properties = buildMapProperties(command);
        command.setMaxPageItems(3);
        RequestUtil.initSearchBeanManual(command);
        Object[] objects = SingletonServiceUtil.getExerciseServiceImplInstance().findExerciseByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<ExerciseDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
        command.setTotalPages((int) Math.ceil((double) command.getTotalItems() / command.getMaxPageItems()));
    }

    private Map<String, Object> buildMapProperties(ExerciseCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getName())) {
            properties.put("name", command.getPojo().getName());
        }
        if (command.getPojo().getType() != null) {
            properties.put("type", command.getPojo().getType());
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
