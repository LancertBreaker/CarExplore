package com.example.carexplore.login;

import com.example.carexplore.login.utils.LoginResult;
import org.json.JSONObject;

import java.sql.*;

import static javax.swing.UIManager.getInt;

public class LoginControler {

    private static LoginControler mInstance;
    public static LoginControler getInstance() {
        if (mInstance == null) {
            mInstance = new LoginControler();
        }
        return mInstance;
    }

    private Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://39.108.54.181:3306/mysql";
        String user = "root";
        String password = "199469";
        return DriverManager.getConnection(url, user, password);
    }

    public String checkUserInfo(String Username, String password) throws SQLException, ClassNotFoundException{
        Connection conn = connection();
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("select * from user_info where account =" + "'" + Username + "'");
        if (resultSet == null) {
            conn.close();
            return LoginResult.empty_name;
        } else if (resultSet.next()) {
            String psw = resultSet.getString("password");
            if (psw.contains(password)) {
                conn.close();
                return LoginResult.result_ok;
            } else {
                conn.close();
                return LoginResult.password_error;
            }
        }
        return LoginResult.error;
    }

    public boolean registerUser(UserBean userBean) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO user_info (account, password, phone, id) VALUES (?, ?, ?, ?)";
        String names = userBean.getName();
        String password = userBean.getPassword();
        String phone = userBean.getPhone();
        int id = getTableCount() + 1;
        Connection connection = connection();
        PreparedStatement  statement = connection.prepareStatement(sql);
        statement.setString(1, names);
        statement.setString(2, password);
        statement.setString(3, phone);
        statement.setInt(4, id);
        int rowsInserted = statement.executeUpdate();
        connection.close();
        return rowsInserted == 1;
    }

    public int getTableCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection connection = connection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM user_info");
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        System.out.println("count size ==" + count);
        connection.close();
        return count;
    }
}
