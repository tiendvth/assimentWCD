package fptaptech.assignmentwcd.controller.category;

import fptaptech.assignmentwcd.entity.Category;
import fptaptech.assignmentwcd.entity.entityEnum.CategoryStatus;
import fptaptech.assignmentwcd.entity.entityEnum.FormAction;
import fptaptech.assignmentwcd.entity.entityEnum.MessageType;
import fptaptech.assignmentwcd.entity.viewEntity.MessageView;
import fptaptech.assignmentwcd.model.MySqlCategoryModel;
import fptaptech.assignmentwcd.model.interfaceModel.CategoryModel;
import fptaptech.assignmentwcd.util.LanguageHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateCategoryServlet", urlPatterns = "/admin/categories/update")
public class UpdateCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(categoryId);
        List<Category> categoryList = categoryModel.findAll();
        req.setAttribute("formAction", FormAction.UPDATE);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("category", category);
        System.out.println("Hello");
        req.getRequestDispatcher("/admin/pages/category/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        if (categoryModel.findById(id) == null) {
            req.getRequestDispatcher("/admin/pages/errors/404.jsp").forward(req, res);
            return;
        }
        String name = req.getParameter("name");
        int status = Integer.parseInt(req.getParameter("status"));
        int parentId = Integer.parseInt(req.getParameter("parentId"));
        Category category = Category.CategoryBuilder.aCategory()
                .withName(name)
                .withStatus(CategoryStatus.of(status))
                .withParentId(parentId)
                .build();
        if (category.isValid()) {
            HttpSession session = req.getSession();
            MessageView messageView = new MessageView();
            if(categoryModel.update(id, category)) {
                messageView.setMessageType(MessageType.SUCCESS);
                messageView.setContent(LanguageHelper.getString("updateCategorySuccess"));
            }else {
                messageView.setMessageType(MessageType.ERROR);
                messageView.setContent(LanguageHelper.getString("updateCategoryFailed"));
            }
            session.setAttribute("message", messageView);
            res.sendRedirect("/admin/categories");
            return;
        }
        req.setAttribute("category", category);
        req.setAttribute("formAction", FormAction.UPDATE);
        req.setAttribute("errors", category.getErrors());
        List<Category> categoryList = categoryModel.findAll();
        req.setAttribute("formAction", FormAction.CREATE);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/admin/pages/category/form.jsp").forward(req, res);
    }
}
