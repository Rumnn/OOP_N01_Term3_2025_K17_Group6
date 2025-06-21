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

    ArrayList<Ticket> tl = new ArrayList<Ticket>();

    public ArrayList<Ticket> ticketList() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
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
                String Name = setdata.getString("movieName");
                String ShowTime = setdata.getString("movieShowTime");
                String DateTime = setdata.getString("movieDateTime");
                String Age = setdata.getString("movieAge");
                String seat = setdata.getString("seat");
                String Price = setdata.getString("price");

                System.out.println("Ticket Aiven test");
                System.out.println("ID: " + id + ", Movie ID: " + ", Movie Name: " + Name +
                        ", Movie Show Time: " + ShowTime + ", Movie Date Time: " + DateTime +
                        ", Movie Age: " + Age + ", Seat: " + seat + ", Price: " + Price);
                t.setId(id);
                t.setName(Name);
                t.getMovie().setShowTime(ShowTime);
                t.getMovie().setDateTime(DateTime);
                t.getMovie().setAge(Integer.parseInt(Age));
                t.setSeat(seat);
                t.setPrice(Double.parseDouble(Price));
                tl.add(t); // Thêm vé vào danh sách
                System.out.println("get ticket aiven");

                System.out.println(t.getId());
                System.out.println(index);

            }
            setdata.close();
            sta.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
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
            ps.setString(3, t.getName());
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
            ps.setString(2, t.getName());
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
