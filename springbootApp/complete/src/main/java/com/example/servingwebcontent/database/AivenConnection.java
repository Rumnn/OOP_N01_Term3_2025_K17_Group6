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
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded successfully");
            
            // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            
            System.out.println("🔗 Connecting to Aiven database...");
            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            
            conn = DriverManager.getConnection(url, user, password);
            
            // Sử dụng biến conn để kiểm tra kết nối
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Kết nối thành công đến Aiven database");
                
                // Test query
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery("SELECT 1");
                    if (rs.next()) {
                        System.out.println("✅ Database query test successful");
                    }
                }
                
                // Kiểm tra các bảng có tồn tại không
                System.out.println("📋 Checking database tables...");
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery("SHOW TABLES");
                    System.out.println("Available tables:");
                    while (rs.next()) {
                        System.out.println("  - " + rs.getString(1));
                    }
                }
                
                conn.close();
                System.out.println("✅ Database connection test completed successfully");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: MySQL Driver không tìm thấy");
            System.out.println("Hãy kiểm tra dependency trong build.gradle");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối đến Aiven database");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Method để test kết nối từ bên ngoài
    public static boolean testConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connection test successful");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Database connection test failed: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}