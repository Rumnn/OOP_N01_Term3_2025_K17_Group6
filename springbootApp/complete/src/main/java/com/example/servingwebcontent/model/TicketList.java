package com.example.servingwebcontent.model;
import com.example.servingwebcontent.model.Movie;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class TicketList {
    private Movie m = new Movie(); // Khởi tạo đối tượng Movie
    ArrayList<Ticket> ticket = new ArrayList<>(); // Danh sách vé

    public ArrayList<Ticket> addTicket(Ticket tickets) {
        ticket.add(tickets); // Thêm vé vào danh sách
        return ticket;
    }
    //Sửa vé
        
    public ArrayList<Ticket> getEditTickets(int id) {
        for (int i = 0; i < ticket.size(); i++) {
            if (Integer.parseInt(ticket.get(i).getId()) == id) {
                ticket.get(i).setId(null); // Cập nhật ID vé
                ticket.get(i).setMovie(m); // Cập nhật thông tin phim
                ticket.get(i).setSeat(null); // Cập nhật ghế
                ticket.get(i).setPrice(0.0); // Cập nhật giá vé
                break;
            }
        }
        return ticket; // Trả về danh sách vé
    }
    // Xóa vé
    public ArrayList<Ticket> getDeleteTickets(int id) {
        for (int i = 0; i < ticket.size(); i++) {
            if (Integer.parseInt(ticket.get(i).getId()) == id) {
                ticket.remove(i); // Xóa vé
                break;
            }
        }
        return ticket; // Trả về danh sách vé
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

    //Kiểm tra giờ chiếu 
    public List<Ticket> printUpComingTickets() {
        List<Ticket> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        for (Ticket t : ticket) {
            try {
                LocalDateTime showTime = LocalDateTime.parse(m.getShowTime(), formatter);
                if (!showTime.isBefore(now) && showTime.isBefore(now.plusHours(1))) {
                    result.add(t);
                }
            } catch (Exception e) {
                // Log lỗi nếu cần
            }
        }
        return result;
    }

    // Thống kê số lượng vé đã đặt
    public int getTicketCount() {
        return ticket.size(); // Trả về số lượng vé đã đặt
    }


}