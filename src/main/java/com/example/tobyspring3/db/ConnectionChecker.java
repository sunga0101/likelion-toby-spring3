package com.example.tobyspring3.db;

import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.util.Map;
import static java.lang.System.getenv;

public class ConnectionChecker {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.check();

    }

    public void check() throws SQLException, ClassNotFoundException{
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver 클래스를 사용하겠다는 의미
        Connection conn = DriverManager.getConnection(      // DB에 연결하겠다
               dbHost, dbUser, dbPassword
        );


        Statement stmt = conn.createStatement();                // 쿼리를 날릴 수 있는 오브젝트 statement
        ResultSet rs = stmt.executeQuery("show databases"); // statement 쿼리의 결과를 받는다
        // 두 문장을 주로 함께 씀

        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }

    }

    public void add() throws SQLException, ClassNotFoundException{
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver 클래스를 사용하겠다는 의미
        Connection conn = DriverManager.getConnection(      // DB에 연결하겠다
                dbHost, dbUser, dbPassword
        );

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        pstmt.setString(1,"2");
        pstmt.setString(2,"김꿀꿀");
        pstmt.setString(3,"kkkjkjk");
        System.out.println(pstmt.executeUpdate()); // 반영된 레코드의 건수 -> 1을 반환

    }

    public void select() throws SQLException, ClassNotFoundException{
        Map<String,String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver"); // Driver 클래스를 사용하겠다는 의미
        Connection conn = DriverManager.getConnection(      // DB에 연결하겠다
                dbHost, dbUser, dbPassword
        );

        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery("select * from users");
        rs = st.getResultSet();

        while (rs.next()) {
            String str1 = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str1 + str2 + str3);
        }

    }
}
