package com.example.servingwebcontent;

import com.example.servingwebcontent.model.TicketList;
import com.example.servingwebcontent.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class TicketController {
    private final TicketList ticketList = new TicketList();

    @GetMapping("/upcoming-tickets")
    public String printUpComingTickets(Model model) {
        try {
            List<Ticket> tickets = ticketList.printUpComingTickets();
            model.addAttribute("tickets", tickets);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi kiểm tra giờ chiếu: " + e.getMessage());
        } finally {
            // Có thể cleanup nếu cần
        }
        return "tickets";
    }
}
