package com.example.servingwebcontent;

import com.example.servingwebcontent.database.DatabaseTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseTestController {
    
    @GetMapping("/database-test")
    public String testDatabase(Model model) {
        try {
            System.out.println("🧪 Testing database connection from web...");
            DatabaseTest.testConnection();
            model.addAttribute("message", "✅ Database test completed successfully! Check console for details.");
        } catch (Exception e) {
            System.out.println("❌ Database test failed: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("message", "❌ Database test failed: " + e.getMessage());
        }
        return "database-test";
    }
} 