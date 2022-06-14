<%--
  Created by IntelliJ IDEA.
  User: tiendangvan
  Date: 13/06/2022
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fptaptech.assignmentwcd.entity.entityEnum.FormAction" %>
<%@ page import="fptaptech.assignmentwcd.entity.Category" %>
<%@ page import="fptaptech.assignmentwcd.entity.Food" %>
<%@ page import="fptaptech.assignmentwcd.constant.ValidationConstant" %>
<%@ page import="fptaptech.assignmentwcd.entity.entityEnum.FoodStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<jsp:include page="/admin/includes/head.jsp">
  <jsp:param name="title" value="Food - Create"/>
</jsp:include>
<%
  FormAction formAction = (FormAction) request.getAttribute("formAction");
  String action = "/admin/foods/create";
  if (formAction == FormAction.UPDATE) {
    action = "/admin/foods/update";
  }
  HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
  if (errors == null) {
    errors = new HashMap<>();
  }
  List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
  if (categoryList == null) {
    categoryList = new ArrayList<>();
  }
  Food food = (Food) request.getAttribute("food");
  if (food == null) {
    food = Food.FoodBuilder.aFood().build();
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
        <form action="<%= action %>" method="post">
          <div class="row">
            <div class="col-lg-8">
              <div class="card card-default">
                <div class="card-header card-header-border-bottom">
                  <h2>Create</h2>
                </div>
                <div class="card-body">
                  <input type="hidden" name="id" value="<%= food.getId() %>"/>
                  <div class="form-row">
                    <div class="col-md-6 mb-3">
                      <label for="name">Food Name</label>
                      <input type="text" class="form-control " id="name"
                             placeholder="Product Name" name="name"
                             value="<%= food.getName() %>"
                      >
                      <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_NAME)) { %>
                      <div class="invalid-feedback">
                        <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_NAME) %>
                      </div>
                      <% } %>
                    </div>
                    <div class="col-md-6 mb-3">
                      <label for="code">Food Code</label>
                      <input type="text" class="form-control " id="code"
                             placeholder="Product Code" name="code"
                             value="<%= food.getCode() %>"
                      >
                      <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_CODE)) { %>
                      <div class="invalid-feedback">
                        <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_CODE) %>
                      </div>
                      <% } %>
                    </div>
                    <div class="col-md-12 mb-3">
                      <label for="price">Price</label>
                      <input type="number" class="form-control " id="price"
                             placeholder="Price" name="price" value="<%= food.getPrice() %>"
                      >
                      <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_PRICE)) { %>
                      <div class="invalid-feedback">
                        <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_PRICE) %>
                      </div>
                      <% } %>
                    </div>
                  </div>
                  <div class="form-row">
                    <div class="col-md-12 mb-3">
                      <label for="description">Description</label>
                      <textarea class="form-control " id="description"
                                placeholder="Description" name="description"
                      ><%= food.getDescription() %></textarea>
                      <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_DESCRIPTION)) { %>
                      <div class="invalid-feedback">
                        <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_DESCRIPTION) %>
                      </div>
                      <% } %>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4">
              <div class="card card-default">
                <div class="card-header card-header-border-bottom">
                </div>
                <div class="card-body">
                  <form action="<%= action %>" method="post">
                    <input type="hidden" name="id" value="<%= food.getId() %>"/>
                    <div class="form-row">
                      <div class="col-md-12 mb-3">
                        <label for="thumbnail">Thumbnail</label>
                        <div>
                          <img src="<%= food.getThumbnail() %>" id="upload-preview" width="300"
                               class="img-responsive img-thumbnail rounded"/>
                        </div>
                        <div class="mt-2">
                          <input type="hidden" multiple class="form-control " id="thumbnail"
                                 placeholder="Thumbnail" name="thumbnail" value="<%= food.getThumbnail() %>"
                          >
                          <button class="btn btn-info" type="button" id="upload_widget">
                            Upload
                          </button>
                        </div>
                        <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_THUMBNAIL)) { %>
                        <div class="invalid-feedback">
                          <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_THUMBNAIL) %>
                        </div>
                        <% } %>
                      </div>
                      <div class="col-md-12 mb-3">
                        <label for="categoryId">Category</label>
                        <select class="form-control " name="categoryId" id="categoryId">
                          <option value="0">Select category</option>
                          <% for (Category item : categoryList) { %>
                          <option value="<%= item.getId() %>" <% if (food.getCategoryId() == item.getId()) { %>
                                  selected <% } %> >
                            <%= item.getName() %>
                          </option>
                          <% } %>
                        </select>
                        <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_CATEGORY_ID)) { %>
                        <div class="invalid-feedback">
                          <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_CATEGORY_ID) %>
                        </div>
                        <% } %>
                      </div>
                      <div class="col-md-12 mb-3">
                        <label for="status">Status</label>
                        <select name="status" class="form-control " id="status">
                          <% for (FoodStatus foodStatus : FoodStatus.values()) { %>
                          <option value="<%= foodStatus.getValue() %>" <% if (food.getStatus().getValue() == foodStatus.getValue()) { %>
                                  selected <% } %> >
                            <%= foodStatus.name() %>
                          </option>
                          <% } %>
                        </select>
                        <% if (errors.containsKey(ValidationConstant.FOOD_ERROR_KEY_STATUS)) { %>
                        <div class="invalid-feedback">
                          <%= errors.get(ValidationConstant.FOOD_ERROR_KEY_STATUS) %>
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
        </form>
      </div>
    </div>
    <jsp:include page="/admin/includes/footer.jsp"/>
  </div>
</div>
<jsp:include page="/admin/includes/script.jsp"/>
<script src="https://cdn.ckeditor.com/ckeditor5/34.1.0/classic/ckeditor.js"></script>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>
<script type="text/javascript">
  ClassicEditor
          .create(document.querySelector('#description'))
          .then(editor => {
            console.log(editor);
          })
          .catch(error => {
            console.error(error);
          });

  var myWidget = cloudinary.createUploadWidget({
            cloudName: 'dadqmakce',
            uploadPreset: 'hello_java'
          }, (error, result) => {
            if (!error && result && result.event === "success") {
              $('#upload-preview').attr("src", result.info.secure_url);
              $('#thumbnail').val(result.info.secure_url);
            }
          }
  )
  document.getElementById("upload_widget").addEventListener("click", function () {
    myWidget.open();
  }, false);

</script>
</body>
</html>

