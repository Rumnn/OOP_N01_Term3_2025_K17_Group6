package main;

import model.Movie;
import model.Ticket;
import model.Customer;

public class App {
    public static void main(String[] args) {
        // Tạo một đối tượng Movie
        Movie movie = new Movie("One Piece", "2025-05-10", 120, "Adventure", 13);

        // Tạo một đối tượng Ticket
        Ticket ticket = new Ticket("T001", movie, "A5", "18:00", 85000);

        // Tạo một đối tượng Customer
        Customer customer = new Customer("KH01", "Nguyen Van A", "a@gmail.com", "0901234567");

        // Hiển thị thông tin khách hàng
        System.out.println("=== Thong tin khach hang ===");
        customer.displayInfo();

        // In thông tin vé
        System.out.println("\n=== Thong tin veve ===");
        ticket.printTicket();

        // Hiển thị thông tin phim
        System.out.println("\n=== Thông Tin Phim ===");
        movie.display();
    }
}