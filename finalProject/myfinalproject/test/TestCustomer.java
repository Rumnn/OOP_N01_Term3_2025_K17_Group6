package test;
import model.Customer;

public class TestCustomer {
    public static void test(String[] args) {
        Customer c = new Customer("KH01", "Nguyen Van A", "a@gmail.com", "0901234567");
        c.displayInfo(); // Kỳ vọng: In ra tên, email và số điện thoại khách
    }
}
