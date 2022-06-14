<%@ page import="fptaptech.assignmentwcd.entity.entityEnum.FormAction" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="fptaptech.assignmentwcd.entity.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="fptaptech.assignmentwcd.constant.ValidationConstant" %>
<%@ page import="fptaptech.assignmentwcd.entity.entityEnum.CategoryStatus" %><%--
  Created by IntelliJ IDEA.
  User: tiendangvan
  Date: 13/06/2022
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<jsp:include page="/admin/includes/head.jsp">
    <jsp:param name="title" value="Category - Create"/>
</jsp:include>
<%
    FormAction formAction = (FormAction) request.getAttribute("formAction");
    String action = "/admin/pages/categories/create";
    if (formAction == FormAction.UPDATE) {
        action = "/admin/pages/categories/update";
    }
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    if (errors == null) {
        errors = new HashMap<>();
    }
    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
    if (categoryList == null) {
        categoryList = new ArrayList<>();
    }
    Category category = (Category) request.getAttribute("category");
    if (category == null) {
        category = Category.CategoryBuilder.aCategory().build();
    }
%>
<body class="sidebar-fixed sidebar-dark header-light header-fixed" id="body">
<!--    <script>-->
<!--      NProgress.configure({ showSpinner: false });-->
<!--      NProgress.start();-->
<!--    </script>-->
<div class="mobile-sticky-body-overlay"></div>
<div class="wrapper">
    <jsp:include page="/admin/includes/sidebar.jsp"/>
    <div class="page-wrapper">
        <jsp:include page="/admin/includes/navbar.jsp"/>
        <div class="content-wrapper">
            <div class="content">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-default">
                            <div class="card-header card-header-border-bottom">
                                <h2>Create</h2>
                            </div>
                            <div class="card-body">
                                <form action="<%= action %>" method="post">
                                    <input type="hidden" name="id" value="<%= category.getId() %>" />
                                    <div class="form-row">
                                        <div class="col-md-12 mb-3">
                                            <label for="name">Category Name</label>
                                            <input type="text" class="form-control " id="name"
                                                   placeholder="First name" name="name" value="<%= category.getName() %>"
                                            >
                                            <% if (errors.containsKey(ValidationConstant.CATEGORY_ERROR_KEY_NAME)) { %>
                                            <div class="invalid-feedback">
                                                <%= errors.get(ValidationConstant.CATEGORY_ERROR_KEY_NAME) %>
                                            </div>
                                            <% } %>

                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label for="parentId">Parent</label>
                                            <select class="form-control " name="parentId" id="parentId">
                                                <option value="0">Select Parent</option>
                                                <% for (Category item : categoryList) { %>
                                                <option value="<%= item.getId() %>" <% if (category.getParentId() == item.getId()) { %>
                                                        selected <% } %> >
                                                    <%= item.getName() %>
                                                </option>
                                                <% } %>
                                            </select>
                                            <% if (errors.containsKey(ValidationConstant.CATEGORY_ERROR_KEY_PARENT_ID_REQUIRED)) { %>
                                            <div class="invalid-feedback">
                                                <%= errors.get(ValidationConstant.CATEGORY_ERROR_KEY_PARENT_ID_REQUIRED) %>
                                            </div>
                                            <% } %>
                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label for="status">Status</label>
                                            <select name="status" class="form-control " id="status">
                                                <option value="0">Select Status</option>
                                                <% for (CategoryStatus categoryStatus : CategoryStatus.values()) { %>
                                                <option value="<%= categoryStatus.getValue() %>" <% if (category.getStatus().getValue() == categoryStatus.getValue()) { %>
                                                        selected <% } %> >
                                                    <%= categoryStatus.name() %>
                                                </option>
                                                <% } %>
                                            </select>
                                            <% if (errors.containsKey(ValidationConstant.CATEGORY_ERROR_KEY_STATUS)) { %>
                                            <div class="invalid-feedback">
                                                <%= errors.get(ValidationConstant.CATEGORY_ERROR_KEY_STATUS) %>
                                            </div>
                                            <% } %>
                                        </div>
                                    </div>
                                    <button class="btn btn-primary" type="submit">Submit form</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/admin/includes/footer.jsp"/>
    </div>
</div>
<jsp:include page="/admin/includes/script.jsp"/>
</body>
</html>