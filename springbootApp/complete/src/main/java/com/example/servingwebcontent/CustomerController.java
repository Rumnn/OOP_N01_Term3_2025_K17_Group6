package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    private List<Customer> customerList = new ArrayList<>();

    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            customerList.add(customer);
            model.addAttribute("message", "Đã thêm khách hàng: " + customer.getName());
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi: " + e.getMessage());
        }
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        try {
            model.addAttribute("customers", customerList);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
        return "customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable int id, Model model) {
        if (id >= 0 && id < customerList.size()) {
            Customer customer = customerList.get(id);
            model.addAttribute("customer", customer);
            model.addAttribute("id", id);
            return "edit-customer";
        } else {
            model.addAttribute("error", "Không tìm thấy khách hàng.");
            model.addAttribute("customers", customerList);
            return "customers";
        }
    }

    @PostMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable int id, @ModelAttribute Customer customer, Model model) {
        if (id >= 0 && id < customerList.size()) {
            customerList.set(id, customer);
            model.addAttribute("message", "Đã cập nhật khách hàng.");
        } else {
            model.addAttribute("error", "Không tìm thấy khách hàng.");
        }
        model.addAttribute("customers", customerList);
        return "customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable int id, Model model) {
        if (id >= 0 && id < customerList.size()) {
            customerList.remove(id);
            model.addAttribute("message", "Đã xóa khách hàng.");
        } else {
            model.addAttribute("error", "Không tìm thấy khách hàng.");
        }
        model.addAttribute("customers", customerList);
        return "customers";
    }
}