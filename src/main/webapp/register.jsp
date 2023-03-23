<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/13
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册</title>
    <%
        request.setCharacterEncoding("utf-8"); //解决中文乱码问题
    %>
</head>
<body>
<center>
    <h1>注册</h1>
    <hr>
    <%
        request.setCharacterEncoding("utf-8"); //解决中文乱码问题
    %>
    <form name="regForm" action="Register" method="post">
        <table>
            <tr>
                <td>账号：</td>
                <td><input type="text" name="account"/>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="phone"/>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td colspan="1"><input type="submit" value="注册"/></td>
                <td colspan="1"><input type="reset" value="重置"/></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
