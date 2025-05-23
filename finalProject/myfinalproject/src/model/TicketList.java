package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TicketList {
    ArrayList<Ticket> tickets = new ArrayList<>(); // Danh sách vé

    public ArrayList<Ticket> addTickets(Ticket ticket) {
        tickets.add(ticket); // Thêm vé vào danh sách
        return tickets;
    }
    public ArrayList<Ticket> getEditTickets(String fullname, int ticketId) {

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getTicketId() == ticketId) {
                tickets.get(i).setFullname(fullname); // Cập nhật tên khách hàng
                break;
            }
        }
        return tickets; // Trả về danh sách vé
    }
    public void printTicketList() {
        int len= tickets.size();
        for (int i=0; i < len; i++) {
                System.out.println("Ticket ID: " + tickets.get(i).getTicketId());

        }
    }


}

public class Ticket {
    private String ticketId; // Mã vé
    private Movie movie; // Phim (gồm tên, thể loại, thời lượng)
    private String seat; // Hàng ghế
    private String showTime; // Thời gian chiếu
    private double price; // Giá
    private String fullname;

    public Ticket(String ticketId, Movie movie, String seat, String showTime, double price) {
        this.ticketId = ticketId;
        this.movie = movie;
        this.seat = seat;
        this.showTime = showTime;
        this.price = price;
    }
    public String getTicketId() {
        return ticketId;
    }
}
