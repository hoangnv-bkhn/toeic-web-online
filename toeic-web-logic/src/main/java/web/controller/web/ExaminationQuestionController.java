package web.controller.web;

import web.command.ExaminationQuestionCommand;
import web.core.common.utils.SessionUtil;
import web.core.dto.ExaminationQuestionDTO;
import web.core.dto.ResultDTO;
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
import java.util.List;

@WebServlet(urlPatterns = {"/bai-thi-thuc-hanh.html","/bai-thi-dap-an.html"})
public class ExaminationQuestionController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExaminationQuestionCommand command = FormUtil.populate(ExaminationQuestionCommand.class, request);
        getExaminationQuestion(command);
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/examination/detail.jsp");
        rd.forward(request, response);
    }

    private void getExaminationQuestion(ExaminationQuestionCommand command) {
        Object[] objects = SingletonServiceUtil.getExaminationQuestionServiceImplInstance().
                findExaminationQuestionByProperties(null, command.getSortExpression(),
                        command.getSortDirection(), null, null, command.getExaminationId());
        command.setListResult((List<ExaminationQuestionDTO>) objects[1]);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExaminationQuestionCommand command = new ExaminationQuestionCommand();
        Integer examinationId = Integer.parseInt(request.getParameter("examinationId"));
        command.setExaminationId(examinationId);
        getExaminationQuestion(command);
        for (ExaminationQuestionDTO item : command.getListResult()) {
            if (request.getParameter("answerUser["+ item.getExaminationQuestionId() +"]") != null){
                item.setAnswerUser(request.getParameter("answerUser["+ item.getExaminationQuestionId() +"]"));
            }
        }
        String userName = (String) SessionUtil.getInstance().getValue(request, WebConstant.LOGIN_NAME);
        ResultDTO resultDTO = SingletonServiceUtil.getResultServiceImplInstance().saveResult(userName, command.getExaminationId(), command.getListResult());
        command.setListenScore(resultDTO.getListenScore());
        command.setReadingScore(resultDTO.getReadingScore());

        request.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/examination/result.jsp");
        rd.forward(request, response);
    }

}
