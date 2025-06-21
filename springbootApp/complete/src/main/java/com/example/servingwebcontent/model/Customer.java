package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer extends ObjectGeneral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    
    @Column(name = "email")
    private String email; // Địa chỉ email
    
    @Column(name = "phone_number")
    private String phoneNumber; // Số điện thoại
    
    public Customer() {
        super("", "");
        this.email = "";
        this.phoneNumber = "";
        
    }
    // Constructor
    public Customer(String id, String name, String email, String phoneNumber) {
        super(id, name);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getDbId() {
        return dbId;
    }
    
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    

    // Phương thức hiển thị thông tin
    public void displayInfo() {
        System.out.println("ID          : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Email       : " + email);
        System.out.println("Number      : " + phoneNumber);
        System.out.println("----------------------------");
    }
}
