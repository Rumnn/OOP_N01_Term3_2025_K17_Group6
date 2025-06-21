package com.example.servingwebcontent;
import com.example.servingwebcontent.model.Ticket;
import com.example.servingwebcontent.database.TicketAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    private TicketAiven ticketAiven = new TicketAiven();

    // Hiển thị danh sách vé
    @GetMapping("/tickets")
    public String listTickets(Model model) {
        try {
            List<Ticket> tickets = ticketAiven.ticketList();
            System.out.println("🔍 TicketController: Found " + tickets.size() + " tickets");
            for (Ticket t : tickets) {
                System.out.println("  - ID: " + t.getId() + ", Movie: " + t.getMovie().getName() + ", Seat: " + t.getSeat());
            }
            model.addAttribute("tickets", tickets);
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong TicketController.listTickets: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi lấy danh sách vé: " + e.getMessage());
        }
        return "ticket-list";
    }

    // Hiển thị form thêm vé
    @GetMapping("/tickets/add")
    public String addTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    // Xử lý thêm vé
    @PostMapping("/tickets/add")
    public String addTicket(@ModelAttribute Ticket ticket, Model model) {
        try {
            ticketAiven.insertTicket(ticket);
            model.addAttribute("message", "Đã thêm vé thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    // Hiển thị form sửa vé
    @GetMapping("/tickets/edit/{id:.+}")
    public String editTicketForm(@PathVariable String id, Model model) {
        List<Ticket> tickets = ticketAiven.ticketList();
        Ticket ticketToEdit = null;
        for (Ticket t : tickets) {
            if (t.getId().equals(id)) {
                ticketToEdit = t;
                break;
            }
        }
        if (ticketToEdit != null) {
            model.addAttribute("ticket", ticketToEdit);
            return "edit-ticket";
        } else {
            model.addAttribute("message", "Không tìm thấy vé để sửa.");
            model.addAttribute("tickets", tickets);
            return "ticket-list";
        }
    }

    // Xử lý cập nhật vé
    @PostMapping("/tickets/edit/{id:.+}")
    public String editTicket(@PathVariable String id, @ModelAttribute Ticket ticket, Model model) {
        try {
            // Validate input
            if (ticket.getMovie().getId() == null || ticket.getMovie().getId().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: ID phim không được để trống");
                model.addAttribute("ticket", ticket);
                return "edit-ticket";
            }
            if (ticket.getSeat() == null || ticket.getSeat().trim().isEmpty()) {
                model.addAttribute("message", "❌ Lỗi: Số ghế không được để trống");
                model.addAttribute("ticket", ticket);
                return "edit-ticket";
            }
            if (ticket.getPrice() <= 0) {
                model.addAttribute("message", "❌ Lỗi: Giá vé phải lớn hơn 0");
                model.addAttribute("ticket", ticket);
                return "edit-ticket";
            }
            
            ticketAiven.updateTicket(ticket);
            model.addAttribute("message", "✅ Đã cập nhật vé thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong TicketController.editTicket: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi cập nhật: " + e.getMessage());
            model.addAttribute("ticket", ticket);
            return "edit-ticket";
        }
        List<Ticket> tickets = ticketAiven.ticketList();
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    // Xóa vé theo id
    @GetMapping("/tickets/delete/{id:.+}")
    public String deleteTicket(@PathVariable String id, Model model) {
        try {
            ticketAiven.deleteTicket(id);
            model.addAttribute("message", "✅ Đã xóa vé thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong TicketController.deleteTicket: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi khi xóa: " + e.getMessage());
        }
        List<Ticket> tickets = ticketAiven.ticketList();
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }
}
