package web.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import web.command.ListenGuidelineCommand;
import web.core.common.utils.UploadUtil;
import web.core.dto.ListenGuidelineDTO;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

//        HttpSession session = request.getSession();
        /*request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        request.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));*/

        /*if (session != null) {
            request.setAttribute(WebConstant.ALERT, session.getAttribute((WebConstant.ALERT)));
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
        }*/

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            executeSearchListenGuideline(request, command);
            request.setAttribute(WebConstant.LIST_ITEMS, command);

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (command.getPojo() != null && command.getPojo().getListenGuidelineId() != null) {
                command.setPojo(SingletonServiceUtil.getListenGuidelineServiceImplInstance().findListenGuidelineById("listenGuidelineId", command.getPojo().getListenGuidelineId()));
            }
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request, response);
        }
       /* session.removeAttribute(WebConstant.ALERT);
        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);*/

    }

    private void executeSearchListenGuideline(HttpServletRequest request, ListenGuidelineCommand command) {
        Map<String, Object> properties = buildMapProperties(command);
        RequestUtil.initSearchBeans(request, command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceImplInstance().findListenGuidelineByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(ListenGuidelineCommand command) {
        Map<String, Object> properties = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(command.getPojo().getTitle())) {
            properties.put("title", command.getPojo().getTitle());
        }
        return properties;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ListenGuidelineCommand command = new ListenGuidelineCommand();


        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();

//        Set<String> valueTitle = buildSetValueListenGuideline();

       /* try {
            Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstant.LISTEN_GUIDE_LINE);
            Map<String, String> mapValue = (Map<String, String>) objects[3];
            command = returnValueListenGuileelineCommand(command, valueTitle, mapValue);

            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.guideline.listen.add.success"));
        } catch (FileUploadException e) {
            log.error(e.getMessage(), e);
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
            e.printStackTrace();
        }*/
        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list");
    }

    /*private ListenGuidelineCommand returnValueListenGuileelineCommand(ListenGuidelineCommand command, Set<String> valueTitle, Map<String, String> mapValue) {
        for (String item : valueTitle) {
            if (mapValue.containsKey(item)) {
                if (item.equals("pojo.title")) {
                    command.getPojo().setTitle(mapValue.get(item));
                } else if (item.equals("pojo.content")) {
                    command.getPojo().setContent(mapValue.get(item));
                }
            }
        }
        return  command;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        return returnValue;
    }*/
}
