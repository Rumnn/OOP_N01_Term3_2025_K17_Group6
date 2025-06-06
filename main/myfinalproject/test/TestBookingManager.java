package test;

import model.Customer;
import model.Movie;
import model.Ticket;
import model.TicketList;

public class TestBookingManager {
    public static void test(String[] args) {

        // Khởi tạo khách hàng (Customer có id, name, email, phone)
        Customer customer1 = new Customer("C001", "Nguyen Van A", "a@gmail.com", "0123456789");
        Customer customer2 = new Customer("C002", "Tran Thi B", "b@gmail.com", "0987654321");

        // Khởi tạo phim (Movie có id, name, showTime, duration, genre, age)
        Movie movie1 = new Movie("M001", "Avengers", "2025-06-06", 150, "Action", 13);
        Movie movie2 = new Movie("M002", "Frozen", "2025-06-07", 90, "Animation", 6);

        // Khởi tạo vé (Ticket có id, name, ticketId, Movie, seat, showTime, price)
        Ticket ticket1 = new Ticket("T001", "Vé 1", "1001", movie1, "A1", "18:00", 90000);
        Ticket ticket2 = new Ticket("T002", "Vé 2", "1002", movie2, "B2", "20:00", 75000);

        // Khởi tạo danh sách vé
        TicketList ticketList = new TicketList();
        ticketList.addTicket(ticket1);
        ticketList.addTicket(ticket2);

        // In danh sach ve theo id
        ticketList.printTicketsByCustomerId();


        // kiem tra gio chieu
        ticketList.printUpComingTickets();
        

        // Thống kê số lượng vé đã đặt
        System.out.println("\nTổng số vé đã đặt: " + ticketList.getTicketCount());

       
    }
}
