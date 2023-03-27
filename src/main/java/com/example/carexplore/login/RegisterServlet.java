package com.example.carexplore.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Register", value = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("account");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        int id = 3;
        try {
            UserBean userBean = new UserBean();
            userBean.setPassword(password);
            userBean.setPhone(phone);
            userBean.setName(name);
            userBean.setId(id);
            boolean b = LoginControler.getInstance().registerUser(userBean);
            response.setContentType("text/json;charset=UTF-8");
            if (b) {
                response.getWriter().append("注册成功");
                response.sendRedirect("index.jsp");
            } else {
                response.getWriter().append("注册失败");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
