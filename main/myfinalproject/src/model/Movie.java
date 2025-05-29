package model;

public class Movie extends ObjectGeneral {     

    private String title;
    private String showTime; // Ngày phát hành
    private int duration; // Thời lượng của phim
    private String genre; // Thể loại phim
    private int age; // Độ tuổi

    public Movie(String id, String name, String showTime, int duration, String genre, int age) {
        super(id, name);
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
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getId() {
        return title + "_" + showTime; // Giả sử ID là sự kết hợp của tên phim và ngày phát hành
    }
    


    public void display() {
        System.out.println("Movie      : " + title);
        System.out.println("Launch Date: " + showTime);
        System.out.println("Duration   : " + duration + " minutes");
        System.out.println("Genre      : " + genre);
        System.out.println("Age Rating : " + age + "+");
    }
}