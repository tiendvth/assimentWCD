package fptaptech.assignmentwcd.controller.category;

import fptaptech.assignmentwcd.entity.Category;
import fptaptech.assignmentwcd.model.MySqlCategoryModel;
import fptaptech.assignmentwcd.model.interfaceModel.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListCategoryServlet", urlPatterns = "/admin/categories")
public class ListCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        List<Category> categoryList = categoryModel.findAll();
        if(categoryList == null) {
            categoryList = new ArrayList<>();
        }
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/admin/pages/category/list.jsp").forward(req, res);
    }
}
