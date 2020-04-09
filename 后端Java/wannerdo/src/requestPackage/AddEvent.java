package requestPackage;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddEvent",urlPatterns = {"/AddEvent"})
public class AddEvent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        String code = request.getParameter("code");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        byte[] b = title.getBytes("ISO-8859-1");//编码
        title = new String(b);//解码:用什么字符集编码就用什么字符集解码

        b = content.getBytes("ISO-8859-1");//编码
        content = new String(b);//解码:用什么字符集编码就用什么字符集解码

        System.out.println("title编码" + Action.getEncoding(title));

        String openid = Action.getOpenid(code);
        //添加event


        int l =  Action.addEvent(openid,title,content);
        System.out.println(l);
    }
}
