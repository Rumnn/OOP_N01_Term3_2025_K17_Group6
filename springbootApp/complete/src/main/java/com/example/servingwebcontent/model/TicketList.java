package com.example.servingwebcontent.model;

import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



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
            if (ticket.get(i).getMovie() != null) {
                System.out.println("Movie : " + ticket.get(i).getMovie().getTitle());
            } else {
                System.out.println("Movie : null");
            }
            System.out.println("Seat: " + ticket.get(i).getSeat());
            System.out.println("Show Time: " + ticket.get(i).getShowTime());
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
    public void printUpComingTickets() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        
        for (Ticket t : ticket) {
            try{
                LocalDateTime showTime = LocalDateTime.parse(t.getShowTime(), formatter);
                if (!showTime.isBefore(now) && showTime.isBefore(now.plusHours(1))) { // Kiểm tra giờ chiếu chưa qua
                    t.displayTicket(); // Hiển thị vé nếu giờ chiếu chưa qua
                }
            } catch (Exception e) {
                
            }
            
        }
    }

    // Thống kê số lượng vé đã đặt
    public int getTicketCount() {
        return ticket.size(); // Trả về số lượng vé đã đặt
    }



}