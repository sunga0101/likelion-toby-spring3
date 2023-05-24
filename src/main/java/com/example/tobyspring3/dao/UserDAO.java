package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;

public abstract class UserDAO {
        ConnectionMaker connectionMaker;

    public UserDAO() {
        this.connectionMaker = new DConnectionMaker();
    }
    public void add() throws ClassNotFoundException, SQLException {

        Connection conn = connectionMaker.makeConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values (?,?,?)");
        pstmt.setString(1,"5");
        pstmt.setString(2,"개똥이");
        pstmt.setString(3, "@12$#%@!");
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();


    }

    // getDataByID
    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();
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
        UserDAO userDao = new NUserDAO();
        userDao.add();

        User user1 = userDao.get("5");
        System.out.println(user1.getId());
        System.out.println(user1.getName());
        System.out.println(user1.getPassword());
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
