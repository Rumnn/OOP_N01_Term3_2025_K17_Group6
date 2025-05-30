package model;

public class Customer extends ObjectGeneral {
    private String email; // Địa chỉ email
    private String phoneNumber; // Số điện thoại
    

    // Constructor
    public Customer(String id, String name, String email, String phoneNumber) {
        super(id, name);
        this.email = email;
        this.phoneNumber = phoneNumber;
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
        System.out.println("NameName    : " + name);
        System.out.println("Email       : " + email);
        System.out.println("NumberNumber: " + phoneNumber);
        System.out.println("----------------------------");
    }
}
