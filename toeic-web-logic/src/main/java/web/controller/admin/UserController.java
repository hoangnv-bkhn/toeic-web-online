package web.controller.admin;

import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import web.command.UserCommand;
import web.core.common.utils.ExcelPoiUtil;
import web.core.common.utils.SessionUtil;
import web.core.common.utils.UploadUtil;
import web.core.dto.RoleDTO;
import web.core.dto.UserDTO;
import web.core.dto.UserImportDTO;
import web.core.web.common.WebConstant;
import web.core.web.utils.FormUtil;
import web.core.web.utils.SingletonServiceUtil;
import web.core.web.utils.WebCommonUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html", "/admin-user-import.html",
        "/admin-user-import-validate.html"})
public class UserController extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

    private final String SHOW_IMPORT_USER = "show_import_user";
    private final String READ_EXCEL = "read_excel";
    private final String VALIDATE_IMPORT = "validate_import";
    private final String LIST_USER_IMPORT = "list_user_import";
    private final String IMPORT_DATA = "import_data";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> mapProperties = new HashMap<String, Object>();
            Object[] objects = SingletonServiceUtil.getUserServiceImplInstance().findByProperty(mapProperties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
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
                    command.setPojo(SingletonServiceUtil.getUserServiceImplInstance().findById(pojo.getUserId()));
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
            command.setRoles(SingletonServiceUtil.getRoleServiceImplInstance().findAll());
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(SHOW_IMPORT_USER)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(VALIDATE_IMPORT)) {
            List<UserImportDTO> userImportDTOS = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request, LIST_USER_IMPORT);
            command.setMaxPageItems(3);
            command.setTotalItems(userImportDTOS.size());
            command.setUserImportDTOS(userImportDTOS);
            request.setAttribute(WebConstant.LIST_ITEMS, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
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

        UploadUtil uploadUtil = new UploadUtil();
        Set<String> value = new HashSet<>();
        value.add("urlType");
        Object[] objects = uploadUtil.writeOrUpdateFile(request, value, "excel");

        try {
            UserCommand command = FormUtil.populate(UserCommand.class, request);
            UserDTO pojo = command.getPojo();
            if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());
                    pojo.setRoleDTO(roleDTO);
                    if (pojo != null && pojo.getUserId() != null) {
                        SingletonServiceUtil.getUserServiceImplInstance().updateUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                    } else {
                        SingletonServiceUtil.getUserServiceImplInstance().saveUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }

                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
                rd.forward(request, response);
            }
            if (objects != null) {
                String urlType = null;
                Map<String, String> mapValue = (Map<String, String>) objects[3];

                for (Map.Entry<String, String> item : mapValue.entrySet()) {
                    if (item.getKey().equals("urlType")) {
                        urlType = item.getValue();
                    }
                }

                if (urlType != null && urlType.equals(READ_EXCEL)) {
                    String fileLocation = objects[1].toString();
                    String fileName = objects[2].toString();

                    List<UserImportDTO> excelValues = returnValueFromExcelFile(fileName, fileLocation);
                    validateData(excelValues);

                    SessionUtil.getInstance().putValue(request, LIST_USER_IMPORT, excelValues);

                    response.sendRedirect("/admin-user-import-validate.html?urlType=validate_import");
                }
            }

            if (command.getUrlType() != null && command.getUrlType().equals(IMPORT_DATA)) {
                List<UserImportDTO> userImportDTOS = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request, LIST_USER_IMPORT);
                SingletonServiceUtil.getUserServiceImplInstance().saveUserImport(userImportDTOS);
                SessionUtil.getInstance().remove(request, LIST_USER_IMPORT);
                response.sendRedirect("/admin-user-list.html?urlType=url_list");
            }

        } catch (Exception e) {
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
            log.error(e.getMessage(), e);
        }

    }

    private void validateData(List<UserImportDTO> excelValues) {
        Set<String> stringSet = new HashSet<String>();
        for (UserImportDTO item : excelValues) {
            checkRequiredField(item);
            validateDuplicateField(item, stringSet);
        }
        SingletonServiceUtil.getUserServiceImplInstance().validateImportUser(excelValues);

    }

    private void validateDuplicateField(UserImportDTO item, Set<String> stringSet) {
        String message = item.getError();
        if (!stringSet.contains(item.getUserName())) {
            stringSet.add(item.getUserName());
        } else {
            if (item.isValid()) {
                message += "<br/>";
                message += bundle.getString("label.username.duplicate");
            }
        }
        if (StringUtils.isNotBlank(message)) {
            item.setValid(false);
            item.setError(message);
        }

    }

    private void checkRequiredField(UserImportDTO item) {
        String message = "";

        if (StringUtils.isBlank(item.getUserName())) {
            message += "<br/>";
            message += bundle.getString("label.username.notempty");
        }
        if (StringUtils.isBlank(item.getPassword())) {
            message += "<br/>";
            message += bundle.getString("label.password.notempty");
        }
        if (StringUtils.isBlank(item.getRoleName())) {
            message += "<br/>";
            message += bundle.getString("label.rolename.notempty");
        }

        if (StringUtils.isNotBlank(message)) {
            item.setValid(false);
        }
        item.setError(message);
    }


    private List<UserImportDTO> returnValueFromExcelFile(String fileName, String fileLocation) throws IOException {
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<UserImportDTO> excelValues = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            UserImportDTO userImportDTO = readDataFromExcelFile(row);


            excelValues.add(userImportDTO);
        }
        return excelValues;
    }

    private UserImportDTO readDataFromExcelFile(Row row) {
        UserImportDTO userImportDTO = new UserImportDTO();

        userImportDTO.setUserName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
        userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
        userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));

        return userImportDTO;
    }
}
