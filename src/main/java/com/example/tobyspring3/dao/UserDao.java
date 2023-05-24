package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.Class.forName;
import static java.lang.System.getenv;

public class UserDao {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );

        return conn;
    }



    public void add() throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values (?,?,?)");
        pstmt.setString(1,"3");
        pstmt.setString(2,"최민지");
        pstmt.setString(3, "@12$#%@!");
        pstmt.executeUpdate();
        pstmt.close();

        conn.close();


    }

    // getDataByID
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select id,name, password from users where id = ?");
        pstmt.setString(1,id);

        ResultSet rs = pstmt.executeQuery();
        rs.next(); // cntl +enter 와 같음

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add();

        User user1 = userDao.get("3");
        System.out.println(user1.getId());
        System.out.println(user1.getName());
        System.out.println(user1.getPassword());
    }
}
