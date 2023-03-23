package com.example.carexplore.login;

import com.example.carexplore.login.utils.LoginResult;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("account");
        String password = req.getParameter("password");
        if (name != null && password != null) {
            try {
                String result = LoginControler.getInstance().checkUserInfo(name, password);
                if (result.equals(LoginResult.result_ok)) {
                    resp.setContentType("text/json;charset=UTF-8");
                    resp.getWriter().append("请求成功");
                    return;
                }

                if (result.equals(LoginResult.empty_name)) {
                    resp.setContentType("text/json;charset=UTF-8");
                    resp.getWriter().append("用户不存在");
                    return;
                }

                if (result.equals(LoginResult.password_error)) {
                    resp.setContentType("text/json;charset=UTF-8");
                    resp.getWriter().append("密码错误");
                    return;
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().append("用户不存在");
        }
    }

    public void destroy() {
    }
}