package controller;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userController")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到输出流
        PrintWriter pw = resp.getWriter();
        //拿到操作码
        String op = req.getParameter("op");
        //System.out.println(op);
        UserService userService  = new UserService();
        if(op.equals("checkLogin")){
            String username = req.getParameter("un");
            String password = req.getParameter("pw");
            //调用业务层完成登录验证功能
            String info = null;
            try {
                info = userService.checkLogin(username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(info.equals("SUCCESS")){
                //保存用户名在session里面
                req.getSession().setAttribute("USERNAME", username);
            }
            //注意事项:在使用ajax请求的过程,不能在后台进行转发和重定向
            //System.out.println(username+"*******"+password);
            pw.write(info);//pw这个流:谁发送了请求，write写的字符串就返回给谁
        }else if (op.equals("success")) {
            List<User> users = null;
            try {
                users = userService.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //将用户集合保存在req里面，以便于接下来的页面使用(用EL表达式使用)
            req.setAttribute("userList", users);
            //进行转发
            req.getRequestDispatcher("/view/user/userList.jsp").forward(req, resp);

        }
        }
}
