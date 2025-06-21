package com.example.servingwebcontent.database;
import com.example.servingwebcontent.env.EnvReader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.model.*;
// import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class CustomerAiven
 {
    public CustomerAiven() {
        // Constructor
    }

    ArrayList <Customer> Cml = new ArrayList<Customer>();

    public ArrayList<Customer> CustomerList() { 

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            Statement sta = conn.createStatement();
            ResultSet setdata = sta.executeQuery("SELECT * FROM customer");
            int index = 0;
            System.out.println("Danh sách khách hàng trong database:");
            int columnCount = setdata.getMetaData().getColumnCount();
            System.out.println("column #"+columnCount);


            while (setdata.next()) {
                // Tạo đối tượng Customer và lấy dữ liệu từ ResultSet
                Customer cm = new Customer();
                // Lấy dữ liệu từ ResultSet
                String id = setdata.getString("id");
                String name = setdata.getString("name");
                String email = setdata.getString("email");
                String phoneNumber = setdata.getString("phoneNumber");


                System.out.println("Customer Aiven test");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber);

                cm.setId(id);
                cm.setName(name);
                cm.setEmail(email);
                cm.setPhoneNumber(phoneNumber);

                System.out.println("get customer aien");
                System.out.println(cm.getName());
                System.out.println(index);

                // Thêm đối tượng Customer vào danh sách
                Cml.add(cm);
            }
            setdata.close();
            sta.close();
            conn.close();


            
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến Aiven database");
            System.out.println(e);
            e.printStackTrace();
        }   
        return Cml;
    }

    public void insertCustomer(Customer cm) {
        try (Connection conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"))) {
            String sql = "INSERT INTO customer (id, name, email, phoneNumber) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cm.getId());
                ps.setString(2, cm.getName());
                ps.setString(3, cm.getEmail());
                ps.setString(4, cm.getPhoneNumber());
                ps.executeUpdate();
            }
            System.out.println("Đã lưu khách hàng vào database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer cm) {
        try (Connection conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"))) {
            String sql = "UPDATE customer SET name=?, email=?, phoneNumber=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cm.getName());
                ps.setString(2, cm.getEmail());
                ps.setString(3, cm.getPhoneNumber());
                ps.setString(4, cm.getId());
                ps.executeUpdate();
            }
            System.out.println("Đã cập nhật khách hàng trong database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String id) {
        try (Connection conn = DriverManager.getConnection(
                EnvReader.get("url"), EnvReader.get("user"), EnvReader.get("password"))) {
            String sql = "DELETE FROM customer WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
            System.out.println("Đã xóa khách hàng khỏi database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}