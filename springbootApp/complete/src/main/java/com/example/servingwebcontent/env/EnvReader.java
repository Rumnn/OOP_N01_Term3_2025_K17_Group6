package com.example.servingwebcontent.env;

import java.io.*;
import java.util.*;

public class EnvReader {
    private static final String ENV_PATH = "src/main/java/com/example/servingwebcontent/env/.env";
    private static final Properties props = new Properties();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim().replaceAll("\"", "");
                    String value = parts[1].trim().replaceAll("\"", "").replaceAll(";", "");
                    props.setProperty(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file .env");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}