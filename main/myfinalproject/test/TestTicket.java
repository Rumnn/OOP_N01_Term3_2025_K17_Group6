package test;

import model.Movie;
import model.Ticket;

public class TestTicket {
    public static void test(String[] args) {
        Movie m = new Movie("One Piece", "2025-05-10", 120, "Adventure", 13); // Thêm tham số age
        Ticket t = new Ticket("T001", m, "A5", "18:00", 85000);
        t.printTicket(); // Kỳ vọng: In ra thông tin vé đúng định dạng
    }
}