package com.example.servingwebcontent.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.env.EnvReader;

import java.sql.*;

public class AivenConnection {
    public void aivenConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
     // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            // Sử dụng biến conn để kiểm tra kết nối
            if (conn != null && !conn.isClosed()) {
                System.out.println("Kết nối thành công đến Aiven database");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến Aiven database");
            e.printStackTrace();
        }
    }
}