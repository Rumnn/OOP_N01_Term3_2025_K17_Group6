package model;

public class Movie {

    private String title;
    private String showTime; // Ngày phát hành
    private int duration; // Thời lượng của phim
    private String genre; // Thể loại phim
    private int age; // Độ tuổi 

    public Movie(String title, String showTime, int duration, String genre, int age) { // Sửa tham số từ launchdate thành showTime
        this.title = title;
        this.showTime = showTime;  
        this.duration = duration;
        this.genre = genre;
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public String getShowTime() {
        return showTime;
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

    public void display() {
        System.out.println("Movie      : " + title);
        System.out.println("Launch Date: " + showTime);
        System.out.println("Duration   : " + duration + " minutes");
        System.out.println("Genre      : " + genre);
        System.out.println("Age Rating : " + age + "+");
    }
}