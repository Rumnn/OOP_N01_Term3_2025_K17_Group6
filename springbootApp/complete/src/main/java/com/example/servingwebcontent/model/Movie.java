package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie extends ObjectGeneral {     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    
    @Column(name = "original_id")
    private String originalId; // ID gốc từ form
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "show_time")
    private String showTime; // Ngày phát hành
    
    @Column(name = "date_time")
    private String dateTime; // Ngày giờ chiếu
    
    @Column(name = "duration")
    private int duration; // Thời lượng của phim
    
    @Column(name = "genre")
    private String genre; // Thể loại phim
    
    @Column(name = "age")
    private int age; // Độ tuổi

    public Movie() {
        super("", ""); // hoặc giá trị mặc định phù hợp với ObjectGeneral
        this.originalId = "";
        this.title = "";
        this.showTime = "";
        this.dateTime = ""; // Hoặc giá trị mặc định khác
        this.duration = 0;
        this.genre = "";
        this.age = 0;
    }

    public Movie(String id, String name, String title, String showTime, String dateTime ,int duration, String genre, int age) {
        super(id, name);
        this.originalId = id;
        this.title = title;
        this.showTime = showTime;
        this.dateTime = dateTime; // Hoặc giá trị mặc định khác
        this.duration = duration;
        this.genre = genre;
        this.age = age;
    }
    
    public Long getDbId() {
        return dbId;
    }
    
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }
    
    public void setId(String id) {
        super.setId(id);
        this.originalId = id;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getName() {
        return super.getName();
    }
    public void setName(String name) {
        super.setName(name);
    }

    public String getTitle() {
        return title;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getAge() {
        return age;
    }
    public boolean isSuitableForAge(int viewerAge) {
    return viewerAge >= age;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    // ID để hiển thị và sử dụng trong URL - sử dụng originalId để đơn giản
    public String getId() {
        if (originalId != null && !originalId.isEmpty()) {
            return originalId;
        }
        // Fallback to parent's ID if originalId is null/empty
        String parentId = super.getId();
        if (parentId != null && !parentId.isEmpty()) {
            return parentId;
        }
        return "";
    }

    // ID để hiển thị với thông tin bổ sung
    public String getDisplayId() {
        return originalId + "_" + showTime.replace("/", "-");
    }

    // ID gốc để lưu vào database
    public String getDatabaseId() {
        return originalId;
    }

    public void display() {
        System.out.println("_____________________________");
        System.out.println("ID         : " + getId());
        System.out.println("Name       : " + getName());
        System.out.println("Movie      : " + title);
        System.out.println("ShowTime   : " + showTime);
        System.out.println("ShowDateTime   : " + dateTime);
        System.out.println("Duration   : " + duration + " minutes");
        System.out.println("Genre      : " + genre);
        System.out.println("Age Rating : " + age + "+");
        System.out.println("_____________________________");
    }
}