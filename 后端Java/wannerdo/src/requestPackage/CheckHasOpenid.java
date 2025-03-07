package requestPackage;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckHasOpenid",urlPatterns = {"/CheckHasOpenid"})
public class CheckHasOpenid extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String code = request.getParameter("code");
        String openid = Action.getOpenid(code);

        //检查user表中是否有这个openid
        try {
            Action.checkHasOpenid(openid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
