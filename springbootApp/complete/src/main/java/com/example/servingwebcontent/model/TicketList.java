package com.example.servingwebcontent.model;
import java.util.ArrayList;
import java.util.List;


public class TicketList {
    ArrayList<Ticket> ticket = new ArrayList<>(); // Danh sách vé

    public ArrayList<Ticket> addTicket(Ticket tickets) {
        ticket.add(tickets); // Thêm vé vào danh sách
        return ticket;
    }

        // In danh sách vé của một khách hàng theo ID

    public void printTicketsByCustomerId(String customerId) {
        System.out.println("--- Danh sách vé theo ID khách hàng: " + customerId + " ---");
        boolean found = false;
        for (Ticket t : ticket) {
            if (t.getId().equals(customerId)) {
                t.displayTicket();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy vé nào cho khách hàng này.");
        }
    }


    // Thống kê số lượng vé đã đặt
    public int getTicketCount() {
        return ticket.size(); // Trả về số lượng vé đã đặt
    }
    // Lấy tất cả vé
    public List<Ticket> getAllTickets() {
        return ticket;
    }

    // Tìm vé theo id
    public Ticket findTicketById(String id) {
        for (Ticket t : ticket) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    // Cập nhật vé theo id
    public boolean updateTicketById(String id, Ticket newTicket) {
        for (int i = 0; i < ticket.size(); i++) {
            if (ticket.get(i).getId().equals(id)) {
                ticket.set(i, newTicket);
                return true;
            }
        }
        return false;
    }

    // Xóa vé theo id
    public boolean removeTicketById(String id) {
        return ticket.removeIf(t -> t.getId().equals(id));
    }

    // In danh sách vé
    public void printTicketList() {
        int len = ticket.size();
        for (int i=0; i < len; i++) {
            System.out.println("Ticket ID: " + ticket.get(i).getId());
            if (ticket.get(i).getMovie() != null) {
                System.out.println("Movie : " + ticket.get(i).getMovie().getName());
            } else {
                System.out.println("Movie : null");
            }
            System.out.println("Seat: " + ticket.get(i).getSeat());
            System.out.println("Show Time: " + ticket.get(i).getMovie().getShowTime());
            System.out.println("Date Time: " + ticket.get(i).getMovie().getDateTime());
            System.out.println("Age: " + ticket.get(i).getMovie().getAge());
            System.out.println("Genre: " + ticket.get(i).getMovie().getGenre());
            System.out.println("Seat: " + ticket.get(i).getSeat()); 
            System.out.println("Price: " + ticket.get(i).getPrice());
            System.out.println("-------------------------");

        }
    }

}