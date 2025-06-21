package com.example.servingwebcontent.database;
import com.example.servingwebcontent.env.EnvReader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.model.*;

public class MovieAiven {
     public MovieAiven() {
        // Constructor
    }

    public ArrayList<Movie> movieList() { 
        ArrayList<Movie> ml = new ArrayList<Movie>(); // Tạo danh sách mới mỗi lần gọi
        
        Connection conn = null;
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded for MovieAiven");
            
            // In thông tin cấu hình database
            System.out.println("🔍 Database Configuration:");
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            
            if (url == null || user == null || password == null) {
                throw new RuntimeException("❌ Database configuration is incomplete. URL: " + (url != null ? "OK" : "NULL") + 
                    ", User: " + (user != null ? "OK" : "NULL") + 
                    ", Password: " + (password != null ? "OK" : "NULL"));
            }
            
            System.out.println("  URL: " + url);
            System.out.println("  User: " + user);
            System.out.println("  Password: " + (password != null ? "***" : "NULL"));
            
            System.out.println("🔌 Connecting to database for movies...");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connection established successfully!");
            
            Statement sta = conn.createStatement();
            ResultSet setdata = sta.executeQuery("SELECT * FROM Movie");
            int index = 0;
            System.out.println("📋 Danh sách phim trong database:");
            int columnCount = setdata.getMetaData().getColumnCount();
            System.out.println("  Column count: " + columnCount);

            while (setdata.next()) {
                Movie m = new Movie();
                // Lấy dữ liệu từ ResultSet
                String id = setdata.getString("id");
                String name = setdata.getString("name");
                String title = setdata.getString("title");
                String showTime = setdata.getString("showTime");
                String dateTime = setdata.getString("dateTime");
                int duration = setdata.getInt("duration");
                String genre = setdata.getString("genre");
                int age = setdata.getInt("age");
                
                System.out.println("  Movie #" + (index + 1) + ":");
                System.out.println("    ID: " + id + ", Name: " + name + ", Title: " + title +
                        ", Show Time: " + showTime + ", Date Time: " + dateTime +
                        ", Duration: " + duration + ", Genre: " + genre + ", Age: " + age);

                m.setId(id);
                m.setName(name);
                m.setTitle(title);
                m.setShowTime(showTime);
                m.setDateTime(dateTime);
                m.setDuration(duration);
                m.setGenre(genre);
                m.setAge(age);
                ml.add(m);
                index++;
            }
            setdata.close();
            sta.close();
            System.out.println("✅ Loaded " + ml.size() + " movies from database successfully");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: MySQL Driver không tìm thấy trong MovieAiven");
            System.out.println("  Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("MySQL Driver not found: " + e.getMessage(), e);
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối đến Aiven database trong MovieAiven");
            System.out.println("  Error: " + e.getMessage());
            System.out.println("  Error Type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("✅ Database connection closed");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return ml;
    }
    
    public void insertMovie(Movie m) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"));
            
            String sql = "INSERT INTO Movie (id, name, title, showTime, dateTime, duration, genre, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("🔍 Inserting movie with ID: " + m.getId());
            System.out.println("🔍 Movie data: " + m.getName() + ", " + m.getTitle());
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, m.getId());
                ps.setString(2, m.getName());
                ps.setString(3, m.getTitle());
                ps.setString(4, m.getShowTime());
                ps.setString(5, m.getDateTime());
                ps.setInt(6, m.getDuration());
                ps.setString(7, m.getGenre());
                ps.setInt(8, m.getAge());
                
                int rowsAffected = ps.executeUpdate();
                System.out.println("✅ Đã thêm " + rowsAffected + " dòng vào database");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieAiven.insertMovie: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm phim: " + e.getMessage(), e);
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

    public void updateMovie(Movie m) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"));
            
            String sql = "UPDATE Movie SET name=?, title=?, showTime=?, dateTime=?, duration=?, genre=?, age=? WHERE id=?";
            System.out.println("🔍 Updating movie with ID: " + m.getId());
            System.out.println("🔍 Movie data: " + m.getName() + ", " + m.getTitle());
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, m.getName());
                ps.setString(2, m.getTitle());
                ps.setString(3, m.getShowTime());
                ps.setString(4, m.getDateTime());
                ps.setInt(5, m.getDuration());
                ps.setString(6, m.getGenre());
                ps.setInt(7, m.getAge());
                ps.setString(8, m.getId());
                
                int rowsAffected = ps.executeUpdate();
                System.out.println("✅ Đã cập nhật " + rowsAffected + " dòng trong database");
                
                if (rowsAffected == 0) {
                    throw new RuntimeException("Không tìm thấy phim với ID: " + m.getId());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieAiven.updateMovie: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật phim: " + e.getMessage(), e);
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

    public void deleteMovie(String id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"));
            
            String sql = "DELETE FROM Movie WHERE id=?";
            System.out.println("🔍 Deleting movie with ID: " + id);
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                
                int rowsAffected = ps.executeUpdate();
                System.out.println("✅ Đã xóa " + rowsAffected + " dòng khỏi database");
                
                if (rowsAffected == 0) {
                    throw new RuntimeException("Không tìm thấy phim với ID: " + id);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong MovieAiven.deleteMovie: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi xóa phim: " + e.getMessage(), e);
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