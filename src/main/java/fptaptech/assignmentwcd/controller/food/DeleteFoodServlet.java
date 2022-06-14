package fptaptech.assignmentwcd.controller.food;

import fptaptech.assignmentwcd.entity.Food;
import fptaptech.assignmentwcd.entity.entityEnum.FoodStatus;
import fptaptech.assignmentwcd.entity.entityEnum.MessageType;
import fptaptech.assignmentwcd.entity.viewEntity.MessageView;
import fptaptech.assignmentwcd.model.MySqlFoodModel;
import fptaptech.assignmentwcd.model.interfaceModel.FoodModel;
import fptaptech.assignmentwcd.util.LanguageHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteFoodServlet", urlPatterns = "/admin/foods/delete")
public class DeleteFoodServlet extends HttpServlet {
    private FoodModel foodModel;

    @Override
    public void init() throws ServletException {
        foodModel = new MySqlFoodModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") == null || req.getParameter("id").length() == 0) {
            req.getRequestDispatcher("/admin/page/errors/404.jsp").forward(req, resp);
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
        Food food = foodModel.findById(id);
        if( food == null) {
            req.getRequestDispatcher("/admin/page/errors/404.jsp").forward(req, resp);
            return;
        }
        MessageView messageView = new MessageView();
        HttpSession session = req.getSession();
        food.setStatus(FoodStatus.DELETED);
        try {
            if(foodModel.update(id, food)) {
                messageView.setMessageType(MessageType.SUCCESS);
                messageView.setContent(LanguageHelper.getString("deleteProductSuccess"));
            }else {
                throw new Exception();
            }
        }catch (Exception e) {
            messageView.setMessageType(MessageType.ERROR);
            messageView.setContent(LanguageHelper.getString("deleteProductFailed"));
        }
        session.setAttribute("message", messageView);
        resp.sendRedirect("/admin/foods");
    }
}
