//其实是list页面的Servlet

package requestPackage;

import action.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import loginObject.Event;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

@WebServlet(name = "WxLogin",urlPatterns = {"/Login"})
public class Login extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String code = request.getParameter("code");

        ObjectMapper objectMapper = new ObjectMapper();

        //获取openid
        String openid = Action.getOpenid(code);
        System.out.println(openid);
        //获取这个人的Event
        Vector<Event> v =  Action.getToDo(openid);

        int l = v.size();
        for(int i = 0;i < l;i++)
        {
            System.out.println(v.get(i));
        }

        //vector 转JSON
        String vJSON = objectMapper.writeValueAsString(v);

        Writer writer = response.getWriter();
        writer.write(vJSON);

    }
}
