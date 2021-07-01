<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>

<%--                    TEST ACTION HIDE WHEN CLICK--%>

<%--                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <h2>This is a heading</h2>
                            <p>This is a paragraph.</p>
                            <p class ="textHide">This is another paragraph.</p>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <button id="btnHide">Click me to hide paragraphs</button>
                        </div>
                    </div>--%>

<%--                    TEST GET VALUE FROM HTML ELEMENT AND SET TO ANOTHER--%>

                   <%-- <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <input type="text" value="JSP-SERVLET" id="value"/>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <p id="showValue">Nothing</p>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <button onclick="usingValAction()">Show info</button>
                        </div>
                    </div>--%>

<%--                    attr() AND prop() METHOD--%>

<%--                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <input type="checkbox" id="testCheckbox" />
                        </div>
                    </div>--%>

<%--                    JQUERY css() METHOD--%>

                    <%--<div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <p style="color: red" id="demoCssMethod1">This is a paragraph</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right"></label>
                        <div class="col-sm-9">
                            <button id="demoCssMethod" onclick="demoCSSMethod()">Change color of text</button>
                        </div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        hideAllWhenClickButton();
    });
    function hideAllWhenClickButton(){
        $("#btnHide").click(function () {
            $(".textHide").hide();
        })
    }

    function usingValAction() {
        var value = $("#value").val();
        $("#showValue").html(value);
    }

    function demoCSSMethod() {
        $("#demoCssMethod1").css("color", "blue");
    }

    <%--var listenGuidelineId = '';--%>
    <%--<c:if test="${not empty item.pojo.listenGuidelineId}">--%>
    <%--listenGuidelineId = ${item.pojo.listenGuidelineId};--%>
    <%--</c:if>--%>
    <%--$(document).ready(function () {--%>
    <%--    var editor = CKEDITOR.replace( 'ListenGuidelineContent' );--%>
    <%--    CKFinder.setupCKEditor( editor, '/ckfinder/' );--%>
    <%--    validateData();--%>
    <%--    $('#uploadImage').change(function () {--%>
    <%--        readURL(this, "viewImage");--%>
    <%--    });--%>
    <%--});--%>
    <%--function validateData() {--%>
    <%--    $('#formEdit').validate({--%>
    <%--        ignore: [],--%>
    <%--        rules: [],--%>
    <%--        messages: []--%>
    <%--    });--%>
    <%--    $("#title").rules( "add", {--%>
    <%--        required: true,--%>
    <%--        messages: {--%>
    <%--            required: '<fmt:message key="label.empty" bundle="${lang}"/>'--%>
    <%--        }--%>
    <%--    });--%>
    <%--    if (listenGuidelineId == '') {--%>
    <%--        $("#uploadImage").rules( "add", {--%>
    <%--            required: true,--%>
    <%--            messages: {--%>
    <%--                required: '<fmt:message key="label.empty" bundle="${lang}"/>'--%>
    <%--            }--%>
    <%--        });--%>
    <%--    }--%>
    <%--    $("#ListenGuidelineContent").rules( "add", {--%>
    <%--        required: function () {--%>
    <%--            CKEDITOR.instances.ListenGuidelineContent.updateElement();--%>
    <%--        },--%>
    <%--        messages: {--%>
    <%--            required: '<fmt:message key="label.empty" bundle="${lang}"/>'--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>
    <%--function readURL(input, imageId) {--%>
    <%--    if (input.files && input.files[0]) {--%>
    <%--        var reader = new FileReader();--%>
    <%--        reader.onload = function (e) {--%>
    <%--            $('#' +imageId).attr('src', reader.result);--%>
    <%--        }--%>
    <%--        reader.readAsDataURL(input.files[0]);--%>
    <%--    }--%>
    <%--}--%>
</script>
</body>
</html>