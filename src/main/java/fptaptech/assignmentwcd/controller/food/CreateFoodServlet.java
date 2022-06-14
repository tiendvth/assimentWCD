package fptaptech.assignmentwcd.controller.food;

import fptaptech.assignmentwcd.entity.Category;
import fptaptech.assignmentwcd.entity.Food;
import fptaptech.assignmentwcd.entity.entityEnum.FoodStatus;
import fptaptech.assignmentwcd.entity.entityEnum.FormAction;
import fptaptech.assignmentwcd.entity.entityEnum.MessageType;
import fptaptech.assignmentwcd.entity.viewEntity.MessageView;
import fptaptech.assignmentwcd.model.MySqlCategoryModel;
import fptaptech.assignmentwcd.model.MySqlFoodModel;
import fptaptech.assignmentwcd.model.interfaceModel.CategoryModel;
import fptaptech.assignmentwcd.model.interfaceModel.FoodModel;
import fptaptech.assignmentwcd.util.LanguageHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateFoodServlet", urlPatterns = "/admin/foods/create")
public class CreateFoodServlet extends HttpServlet {
    private FoodModel foodModel;
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        foodModel = new MySqlFoodModel();
        categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryModel.findAll();
        req.setAttribute("formAction", FormAction.CREATE);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/admin/pages/food/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        String thumbnail = req.getParameter("thumbnail");
        String code = req.getParameter("code");
        String description = req.getParameter("description");
        double price = 0;
        if(req.getParameter("price") != null) {
            price = Double.parseDouble(req.getParameter("price"));
        }
        FoodStatus status = FoodStatus.of(Integer.parseInt(req.getParameter("status")));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Food food = Food.FoodBuilder.aFood()
                .withName(name)
                .withCode(code)
                .withThumbnail(thumbnail)
                .withPrice(price)
                .withStatus(status)
                .withCategoryId(categoryId)
                .withDescription(description)
                .build();
        HttpSession session = req.getSession();
        MessageView messageView = new MessageView();
        try {
            if(food.isValid()) {
                if(foodModel.create(food)) {
                    messageView.setMessageType(MessageType.SUCCESS);
                    messageView.setContent(LanguageHelper.getString("addProductSuccess"));
                }else {
                    throw new Exception();
                }
            }else {
                List<Category> categoryList = categoryModel.findAll();
                req.setAttribute("formAction", FormAction.CREATE);
                req.setAttribute("categoryList", categoryList);
                req.setAttribute("food", food);
                req.setAttribute("errors", food.getErrors());
                req.getRequestDispatcher("/admin/pages/food/form.jsp").forward(req, resp);
                return;
            }
        }catch (Exception e) {
            messageView.setMessageType(MessageType.ERROR);
            messageView.setContent(LanguageHelper.getString("addProductFailed"));
        }
        session.setAttribute("message", messageView);
        resp.sendRedirect("/admin/foods");
    }
}
