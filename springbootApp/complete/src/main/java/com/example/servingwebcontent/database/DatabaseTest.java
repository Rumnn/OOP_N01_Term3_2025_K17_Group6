package com.example.servingwebcontent.database;

import com.example.servingwebcontent.env.EnvReader;
import com.example.servingwebcontent.env.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    
    public static void testConnection() {
        System.out.println("🧪 Testing Database Connection...");
        
        // Test EnvReader configuration
        System.out.println("\n📋 Testing EnvReader Configuration:");
        EnvReader.printConfig();
        
        // Test DatabaseConfig
        System.out.println("\n📋 Testing DatabaseConfig:");
        DatabaseConfig.printConfig();
        
        Connection conn = null;
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded successfully");
            
            // Get connection parameters
            String url = EnvReader.get("url");
            String user = EnvReader.get("user");
            String password = EnvReader.get("password");
            
            if (url == null || user == null || password == null) {
                System.out.println("❌ Database configuration is incomplete!");
                System.out.println("  URL: " + (url != null ? "OK" : "NULL"));
                System.out.println("  User: " + (user != null ? "OK" : "NULL"));
                System.out.println("  Password: " + (password != null ? "OK" : "NULL"));
                return;
            }
            
            System.out.println("\n🔌 Attempting to connect to database...");
            System.out.println("  URL: " + url);
            System.out.println("  User: " + user);
            
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connection successful!");
            
            // Test basic query first
            System.out.println("\n📋 Testing basic database query...");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1 as test");
            
            if (rs.next()) {
                System.out.println("✅ Basic query successful!");
            }
            
            rs.close();
            
            // Check if tables exist
            System.out.println("\n📋 Checking database tables...");
            rs = stmt.executeQuery("SHOW TABLES");
            boolean movieTableExists = false;
            boolean customerTableExists = false;
            boolean ticketTableExists = false;
            
            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("  - Found table: " + tableName);
                if (tableName.equalsIgnoreCase("Movie")) movieTableExists = true;
                if (tableName.equalsIgnoreCase("Customer")) customerTableExists = true;
                if (tableName.equalsIgnoreCase("Ticket")) ticketTableExists = true;
            }
            
            rs.close();
            
            // Test Movie table query if it exists
            if (movieTableExists) {
                System.out.println("\n📋 Testing Movie table query...");
                rs = stmt.executeQuery("SELECT COUNT(*) as count FROM Movie");
                
                if (rs.next()) {
                    int count = rs.getInt("count");
                    System.out.println("✅ Movie table query successful! Found " + count + " movies in database");
                } else {
                    System.out.println("⚠️ Movie table query returned no results");
                }
                rs.close();
            } else {
                System.out.println("⚠️ Movie table does not exist yet. It will be created when the application starts.");
            }
            
            if (!customerTableExists) {
                System.out.println("⚠️ Customer table does not exist yet. It will be created when the application starts.");
            }
            
            if (!ticketTableExists) {
                System.out.println("⚠️ Ticket table does not exist yet. It will be created when the application starts.");
            }
            
            stmt.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found: " + e.getMessage());
            System.out.println("  Please check if mysql-connector-j dependency is included in build.gradle");
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            System.out.println("  SQL State: " + e.getSQLState());
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  Please check:");
            System.out.println("    1. Database server is running");
            System.out.println("    2. Network connectivity to " + EnvReader.get("url"));
            System.out.println("    3. Username and password are correct");
            System.out.println("    4. SSL configuration is correct");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
            System.out.println("  Error Type: " + e.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("✅ Database connection closed");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error closing connection: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        testConnection();
    }
} 