package com.example.servingwebcontent.database;
import com.example.servingwebcontent.env.EnvReader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.servingwebcontent.model.*;

public class MovieAiven {
     public MovieAiven() {
        // Constructor
    }

    ArrayList <Movie> ml = new ArrayList<Movie>();

      public ArrayList<Movie> movieList() { 

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối đúng thông tin Aiven
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            conn = DriverManager.getConnection(url, user, password);
            Statement sta = conn.createStatement();
            ResultSet setdata = sta.executeQuery("SELECT * FROM Movie");
            int index = 0;
            System.out.println("Danh Sach phim trong database:");
            int columnCount = setdata.getMetaData().getColumnCount();
            System.out.println("column #"+columnCount);


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
                System.out.println("Movie Aiven test");
                System.out.println("ID: " + id + ", Name: " + name + ", Title: " + title +
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
                System.out.println("get movie aien");
                System.out.println(m.getName());
                System.out.println(index);
                
            }
            setdata.close();
            sta.close();
            conn.close();


            
        } catch (Exception e) {
            System.out.println("Lỗi kết nối đến Aiven database");
            System.out.println(e);
            e.printStackTrace();
        }   
        return ml;
    }
    
}