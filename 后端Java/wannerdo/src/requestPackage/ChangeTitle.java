package requestPackage;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeTitle",urlPatterns = {"/ChangeTitle"})
public class ChangeTitle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String title = request.getParameter("title");

        byte[] b = title.getBytes("ISO-8859-1");//编码
        title = new String(b);//解码:用什么字符集编码就用什么字符集解码

        int IntId = Integer.parseInt(id);
        Action.changeTitle(IntId,title);
    }
}
