package com.example.servingwebcontent.database;

import java.sql.*;

public class AivenConnection {
    public void aivenConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối đúng thông tin Aiven
            String url = "jdbc:mysql://mysql-1bf49a9c-nghiengame005.c.aivencloud.com:27021/cinema_db?ssl-mode=REQUIRED";
            String user = "avnadmin";
            String password = "AVNS_OY6UdTSUCEJY08Wic_V";
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến Aiven database");
            e.printStackTrace();
        }
    }
}