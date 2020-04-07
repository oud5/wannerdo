package action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import http.HttpRequest;
import loginObject.Event;
import loginObject.Res;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Action {

    public static Vector getToDo(String openid) throws JsonProcessingException {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result;
        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "select id,title,content from event where openid = ?";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,openid);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句，查user表中是否有这个用户


        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Vector<Event> v = new Vector<Event>();
        try {
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Event e = new Event(id,title,content);
                v.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
        if(rs != null){   // 关闭记录集
           try{
            rs.close() ;
        }catch(SQLException e){
            e.printStackTrace() ;
        }
          }
      if(stmt != null){   // 关闭声明
        try{
            stmt.close() ;
        }catch(SQLException e){
            e.printStackTrace() ;
        }
          }
      if(con != null){  // 关闭连接对象
         try{
            con.close() ;
         }catch(SQLException e){
            e.printStackTrace() ;
         }
       }

        //**********************
        return v;

    }

    public static String getOpenid(String code) throws IOException {
        String appid = "wx1aa4ef7b0f91d988";
        String secret = "7722aac51770a97c408165663590b2e0";
        String js_code = code;
        String param = "appid=" + appid + "secret=" + secret + "js_code=" + code + "grant_type=authorization_code";
        String address = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);//这是该接口需要的参数
        params.put("secret", secret);//这是该接口需要的参数
        params.put("js_code", code);//这是该接口需要的参数
        params.put("grant_type", "authorization_code");//这是该接口需要的参数
        String res = null;
        try {
            res = HttpRequest.get(address, params, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        //JSON转Object
        Res res1 = objectMapper.readValue(res,Res.class);
        return res1.getOpenid();
    }

    public static int addEvent(String openid, String title, String content) {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;

        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "INSERT INTO event (openid,title,content) values(?,?,?)";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,openid);
            stmt.setString(2,title);
            stmt.setString(3,content);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
      if(stmt != null){   // 关闭声明
          try{
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace() ;
        }
      }
      if(con != null){  // 关闭连接对象
         try{
            con.close() ;
         }catch(SQLException e){
            e.printStackTrace() ;
         }
       }
        return rs;
    }

    private static void addUser(String openid) {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;

        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "INSERT INTO user VALUES (?,0)";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,openid);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
        if(stmt != null){   // 关闭声明
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(con != null){  // 关闭连接对象
            try{
                con.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }

    }
    public static void checkHasOpenid(String openid) throws SQLException {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "select * from user where openid = ?";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,openid);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //检查rs里的内容
        if(!rs.next())  //user表中没有这个用户 加到user中
        {
            Action.addUser(openid);
        }

        //关闭连接
        if(stmt != null){   // 关闭声明
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(con != null){  // 关闭连接对象
            try{
                con.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
    }

    public static void deleteEvent(int id) {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;

        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = " DELETE FROM event where id = ?";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);

        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
        if(stmt != null){   // 关闭声明
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(con != null){  // 关闭连接对象
            try{
                con.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
    }

    public static void changeTitle(int id, String title) {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;

        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "UPDATE event SET title= ? where id=?";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setInt(2,id);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
        if(stmt != null){   // 关闭声明
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(con != null){  // 关闭连接对象
            try{
                con.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
    }

    public static void changeContent(int id, String content) {
        //访问数据库***********
        // JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/wannerdo?serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "089012";

        //JSON转化
        ObjectMapper objectMapper = new ObjectMapper();

        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;

        //加载JDBC驱动
        //-----------------------
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            /*
            可以删除
             */
            System.out.println("加载驱动出错");
            e.printStackTrace();
        }

        //创建数据库的连接
        try {
            con = DriverManager.getConnection(DB_URL,USER, PASS);
        } catch (SQLException e) {
            /*
            可以删除
             */
            System.out.println("连接出错");
            e.printStackTrace();
        }
        String sql = "UPDATE event SET content= ? where id=?";
        System.out.println(sql);
        //创建一个Statement
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,content);
            stmt.setInt(2,id);
        } catch (SQLException e) {
            System.out.println("创建Statement出错");
            e.printStackTrace();
        }
        //执行SQL语句

        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //关闭连接
        if(stmt != null){   // 关闭声明
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
        if(con != null){  // 关闭连接对象
            try{
                con.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
    }
}
