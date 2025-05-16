package model;

public class Customer {
    private String id; // Mã khách hàng
    private String name; // Tên khách hàng
    private String email; // Địa chỉ email
    private String phoneNumber; // Số điện thoại
    

    // Constructor
    public Customer(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Phương thức hiển thị thông tin
    public void displayInfo() {
        System.out.println("NameName    : " + name);
        System.out.println("Email       : " + email);
        System.out.println("NumberNumber: " + phoneNumber);
    }
}
