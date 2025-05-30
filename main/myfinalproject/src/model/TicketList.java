package model;

import java.util.ArrayList;



public class TicketList {
    ArrayList<Ticket> ticket = new ArrayList<>(); // Danh sách vé

    public ArrayList<Ticket> addTicket(Ticket tickets) {
        ticket.add(tickets); // Thêm vé vào danh sách
        return ticket;
    }
    public ArrayList<Ticket> getEditTickets(int ticketId) {
        for (int i = 0; i < ticket.size(); i++) {
            if (Integer.parseInt(ticket.get(i).getTicketId()) == ticketId) {
                ticket.get(i).setTicketId(String.valueOf(ticketId)); // Cập nhật vé
                break;
            }
        }
        return ticket; // Trả về danh sách vé
    }
    public ArrayList<Ticket> getDeleteTickets(int ticketId) {
        for (int i = 0; i < ticket.size(); i++) {
            if (Integer.parseInt(ticket.get(i).getTicketId()) == ticketId) {
                ticket.remove(i); // Xóa vé
                break;
            }
        }
        return ticket; // Trả về danh sách vé
    }
    public void printTicketList() {
        int len = ticket.size();
        for (int i=0; i < len; i++) {
            System.out.println("Ticket ID: " + ticket.get(i).getTicketId());
            System.out.println("Movie : " + ticket.get(i).getMovie().getTitle());
            System.out.println("Seat: " + ticket.get(i).getSeat());
            System.out.println("Show Time: " + ticket.get(i).getShowTime());
            System.out.println("Price: " + ticket.get(i).getPrice());
            System.out.println("-------------------------");

        }
    }
}