package com.example.servingwebcontent;

import com.example.servingwebcontent.model.TicketList;
import com.example.servingwebcontent.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {
    private final TicketList ticketList = new TicketList();

    // Hiển thị danh sách vé
    @GetMapping("/tickets")
    public String listTickets(Model model) {
        model.addAttribute("tickets", ticketList.getAllTickets());
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
            ticketList.addTicket(ticket);
            model.addAttribute("message", "Đã thêm vé thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        model.addAttribute("ticket", new Ticket());
        return "add-ticket";
    }

    // Hiển thị form sửa vé
    @GetMapping("/tickets/edit/{id}")
    public String editTicketForm(@PathVariable String id, Model model) {
        Ticket ticketToEdit = ticketList.findTicketById(id);
        if (ticketToEdit != null) {
            model.addAttribute("ticket", ticketToEdit);
            return "edit-ticket";
        } else {
            model.addAttribute("message", "Không tìm thấy vé để sửa.");
            model.addAttribute("tickets", ticketList.getAllTickets());
            return "ticket-list";
        }
    }

    // Xử lý cập nhật vé
    @PostMapping("/tickets/edit/{id}")
    public String editTicket(@PathVariable String id, @ModelAttribute Ticket ticket, Model model) {
        boolean updated = ticketList.updateTicketById(id, ticket);
        if (updated) {
            model.addAttribute("message", "Đã cập nhật vé thành công!");
        } else {
            model.addAttribute("message", "Không tìm thấy vé để cập nhật.");
        }
        model.addAttribute("tickets", ticketList.getAllTickets());
        return "ticket-list";
    }

    // Xóa vé theo id
    @GetMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable String id, Model model) {
        ticketList.removeTicketById(id);
        model.addAttribute("tickets", ticketList.getAllTickets());
        model.addAttribute("message", "Đã xóa vé thành công!");
        return "ticket-list";
    }
}
