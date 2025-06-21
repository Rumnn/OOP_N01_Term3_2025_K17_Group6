package com.example.servingwebcontent.env;

import java.io.*;
import java.util.*;

public class EnvReader {
    private static final Properties props = new Properties();

    static {
        try {
            System.out.println("📋 Loading database configuration from DatabaseConfig...");
            // Sử dụng DatabaseConfig làm primary source
            props.setProperty("url", DatabaseConfig.getUrl());
            props.setProperty("user", DatabaseConfig.getUser());
            props.setProperty("password", DatabaseConfig.getPassword());
            
            System.out.println("✅ Database configuration loaded successfully");
        } catch (Exception e) {
            System.out.println("❌ Error loading database configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            System.out.println("⚠️ Key not found in configuration: " + key);
            return null;
        }
        return value;
    }
    
    public static void printConfig() {
        System.out.println("🔍 EnvReader Configuration:");
        System.out.println("  URL: " + get("url"));
        System.out.println("  User: " + get("user"));
        System.out.println("  Password: " + (get("password") != null ? "***" : "NULL"));
    }
}