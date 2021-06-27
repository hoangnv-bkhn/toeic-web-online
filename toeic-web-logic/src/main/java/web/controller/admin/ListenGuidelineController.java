package web.controller.admin;

import web.command.ListenGuidelineCommand;
import web.core.dto.ListenGuidelineDTO;
import web.core.web.common.WebConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ListenGuidelineCommand command = new ListenGuidelineCommand();
        List<ListenGuidelineDTO> listenGuidelineDTOs = new ArrayList<>();

        ListenGuidelineDTO dto1 = new ListenGuidelineDTO();
        dto1.setTitle("Bai huong dan nghe 1");
        dto1.setContent("Noi dung bai huong dan nghe 1");

        ListenGuidelineDTO dto2 = new ListenGuidelineDTO();
        dto2.setTitle("Bai huong dan nghe 2");
        dto2.setContent("Noi dung bai huong dan nghe 2");

        listenGuidelineDTOs.add(dto1);
        listenGuidelineDTOs.add(dto2);

        command.setListResult(listenGuidelineDTOs);
        command.setMaxPageItems(1);
        command.setTotalItems(listenGuidelineDTOs.size());

        request.setAttribute(WebConstant.LIST_ITEMS, command);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
