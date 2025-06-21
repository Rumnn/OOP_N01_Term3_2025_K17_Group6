package com.example.servingwebcontent.database;
import com.example.servingwebcontent.env.EnvReader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.model.*;

public class TicketAiven {

    public TicketAiven() {
        // Constructor
    }

    public ArrayList<Ticket> ticketList() {
        ArrayList<Ticket> tl = new ArrayList<Ticket>(); // Tạo danh sách mới mỗi lần gọi
        
        Connection conn = null;
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded for TicketAiven");
            
            // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            
            System.out.println("Connecting to database for tickets...");
            conn = DriverManager.getConnection(url, user, password);
            
            Statement sta = conn.createStatement();
            ResultSet setdata = sta.executeQuery("SELECT * FROM Ticket");
            int index = 0;
            System.out.println("Danh Sach ve trong database:");
            int columnCount = setdata.getMetaData().getColumnCount();
            System.out.println("column #"+columnCount);

            while (setdata.next()) {
                Ticket t = new Ticket();
                // Lấy dữ liệu từ ResultSet
                String id = setdata.getString("id");
                String movieId = setdata.getString("movieId");
                String movieName = setdata.getString("movieName");
                String showTime = setdata.getString("movieShowTime");
                String dateTime = setdata.getString("movieDateTime");
                int age = setdata.getInt("movieAge");
                String seat = setdata.getString("seat");
                double price = setdata.getDouble("price");

                System.out.println("Ticket Aiven test");
                System.out.println("ID: " + id + ", Movie ID: " + movieId + ", Movie Name: " + movieName +
                        ", Movie Show Time: " + showTime + ", Movie Date Time: " + dateTime +
                        ", Movie Age: " + age + ", Seat: " + seat + ", Price: " + price);
                
                t.setId(id);
                t.getMovie().setId(movieId);
                t.getMovie().setName(movieName);
                t.getMovie().setShowTime(showTime);
                t.getMovie().setDateTime(dateTime);
                t.getMovie().setAge(age);
                t.setSeat(seat);
                t.setPrice(price);
                tl.add(t); // Thêm vé vào danh sách
                System.out.println("get ticket aiven");
                System.out.println(t.getId());
                System.out.println(index);
            }
            setdata.close();
            sta.close();
            conn.close();
            System.out.println("✅ Loaded " + tl.size() + " tickets from database");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: MySQL Driver không tìm thấy trong TicketAiven");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối đến Aiven database trong TicketAiven");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tl;
    }

    public void insertTicket(Ticket t) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO Ticket (id, movieId, movieName, movieShowTime, movieDateTime, movieAge, seat, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getId());
            ps.setString(2, t.getMovie().getId());
            ps.setString(3, t.getMovie().getName());
            ps.setString(4, t.getMovie().getShowTime());
            ps.setString(5, t.getMovie().getDateTime());
            ps.setInt(6, t.getMovie().getAge());
            ps.setString(7, t.getSeat());
            ps.setDouble(8, t.getPrice());
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("Đã lưu vé vào database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTicket(Ticket t) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE Ticket SET movieId=?, movieName=?, movieShowTime=?, movieDateTime=?, movieAge=?, seat=?, price=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getMovie().getId());
            ps.setString(2, t.getMovie().getName());
            ps.setString(3, t.getMovie().getShowTime());
            ps.setString(4, t.getMovie().getDateTime());
            ps.setInt(5, t.getMovie().getAge());
            ps.setString(6, t.getSeat());
            ps.setDouble(7, t.getPrice());
            ps.setString(8, t.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("Đã cập nhật vé trong database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(String id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "DELETE FROM Ticket WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            System.out.println("Đã xóa vé khỏi database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
