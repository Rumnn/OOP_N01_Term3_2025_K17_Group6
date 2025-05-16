package test;
import model.Movie;

public class TestMovie {
    public static void test(String[] args) {
        Movie m = new Movie("Avengers", "2025-05-01", 150, "Action", 16); // Thêm tham số age
        m.display();  // Sửa từ displayInfo() thành display()
    }
}