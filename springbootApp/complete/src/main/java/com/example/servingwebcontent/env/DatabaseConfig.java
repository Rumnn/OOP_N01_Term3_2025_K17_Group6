package com.example.servingwebcontent.env;

public class DatabaseConfig {
    // Thông tin kết nối Aiven database với SSL
    public static final String DB_URL = "jdbc:mysql://mysql-1bf49a9c-nghiengame005.c.aivencloud.com:27021/cinema_db?ssl-mode=REQUIRED&allowPublicKeyRetrieval=true&useSSL=true&requireSSL=true&verifyServerCertificate=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    public static final String DB_USER = "avnadmin";
    public static final String DB_PASSWORD = "AVNS_OY6UdTSUCEJY08Wic_V";
    
    // Hoặc sử dụng biến môi trường
    public static String getUrl() {
        String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : DB_URL;
        System.out.println("🔍 Database URL: " + url);
        return url;
    }
    
    public static String getUser() {
        String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : DB_USER;
        System.out.println("🔍 Database User: " + user);
        return user;
    }
    
    public static String getPassword() {
        String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : DB_PASSWORD;
        System.out.println("🔍 Database Password: " + (password != null ? "***" : "NULL"));
        return password;
    }
    
    public static void printConfig() {
        System.out.println("🔍 Database Configuration:");
        System.out.println("  URL: " + getUrl());
        System.out.println("  User: " + getUser());
        System.out.println("  Password: " + (getPassword() != null ? "***" : "NULL"));
    }
} 