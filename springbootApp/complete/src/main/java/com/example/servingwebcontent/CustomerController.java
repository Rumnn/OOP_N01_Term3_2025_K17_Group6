package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Customer;
import com.example.servingwebcontent.database.CustomerAiven;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerAiven customerAiven = new CustomerAiven();

    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            // Validate input
            if (customer.getId() == null || customer.getId().trim().isEmpty()) {
                model.addAttribute("message", "Lỗi: ID không được để trống");
                model.addAttribute("customer", customer);
                return "add-customer";
            }
            if (customer.getName() == null || customer.getName().trim().isEmpty()) {
                model.addAttribute("message", "Lỗi: Tên không được để trống");
                model.addAttribute("customer", customer);
                return "add-customer";
            }
            
            customerAiven.insertCustomer(customer);
            model.addAttribute("message", "✅ Đã thêm khách hàng thành công: " + customer.getName());
            model.addAttribute("customer", new Customer());
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong CustomerController: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Lỗi: " + e.getMessage());
            model.addAttribute("customer", customer);
        }
        return "add-customer";
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        try {
            List<Customer> customers = customerAiven.CustomerList();
            System.out.println("🔍 CustomerController: Found " + customers.size() + " customers");
            for (Customer c : customers) {
                System.out.println("  - ID: " + c.getId() + ", Name: " + c.getName() + ", Email: " + c.getEmail());
            }
            model.addAttribute("customers", customers);
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong CustomerController.showCustomerList: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
        return "customers";
    }

    @GetMapping("/customers/edit/{id:.+}")
    public String showEditCustomerForm(@PathVariable String id, Model model) {
        // Tìm khách hàng theo ID string từ database
        List<Customer> customers = customerAiven.CustomerList();
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                customer = c;
                break;
            }
        }
        
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("id", id);
            return "edit-customer";
        } else {
            model.addAttribute("error", "Không tìm thấy khách hàng.");
            model.addAttribute("customers", customers);
            return "customers";
        }
    }

    @PostMapping("/customers/edit/{id:.+}")
    public String editCustomer(@PathVariable String id, @ModelAttribute Customer customer, Model model) {
        try {
            // Validate input
            if (customer.getName() == null || customer.getName().trim().isEmpty()) {
                model.addAttribute("error", "Lỗi: Tên không được để trống");
                model.addAttribute("customer", customer);
                model.addAttribute("id", id);
                return "edit-customer";
            }
            if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
                model.addAttribute("error", "Lỗi: Email không được để trống");
                model.addAttribute("customer", customer);
                model.addAttribute("id", id);
                return "edit-customer";
            }
            
            customerAiven.updateCustomer(customer);
            model.addAttribute("message", "✅ Đã cập nhật khách hàng thành công: " + customer.getName());
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong CustomerController.editCustomer: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "❌ Lỗi khi cập nhật: " + e.getMessage());
            model.addAttribute("customer", customer);
            model.addAttribute("id", id);
            return "edit-customer";
        }
        List<Customer> customers = customerAiven.CustomerList();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/delete/{id:.+}")
    public String deleteCustomer(@PathVariable String id, Model model) {
        try {
            customerAiven.deleteCustomer(id);
            model.addAttribute("message", "✅ Đã xóa khách hàng thành công");
        } catch (Exception e) {
            System.out.println("❌ Lỗi trong CustomerController.deleteCustomer: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "❌ Lỗi khi xóa: " + e.getMessage());
        }
        List<Customer> customers = customerAiven.CustomerList();
        model.addAttribute("customers", customers);
        return "customers";
    }
}