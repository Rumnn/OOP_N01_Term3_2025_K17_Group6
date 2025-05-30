package model;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerList {
    private ArrayList<Customer> cus = new ArrayList<Customer>();

    // Thêm khách hàng
    public void addCustomer(Customer customer) {
        cus.add(customer);
    }

    // Sửa tên khách hàng theo ID
    public void editCustomerName(String customerId, String newName) {
        for (Customer c : cus) {
            if (c.getId().equals(customerId)) {
                c.setName(newName);
                System.out.println("Đã cập nhật tên khách hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng có ID: " + customerId);
    }

    // Xóa khách hàng theo ID
    public void deleteCustomer(String customerId) {
        for (int i = 0; i < cus.size(); i++) {
            if (cus.get(i).getId().equals(customerId)) {
                cus.remove(i);
                System.out.println("Đã xóa khách hàng có ID: " + customerId);
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng có ID: " + customerId);
    }

    // In danh sách khách hàng
    public void printCustomerList() {
        if (cus.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
        } else {
            System.out.println("Danh sách khách hàng:");
            for (Customer c : cus) {
                c.displayInfo();
            }
        }
    }

    // Tìm khách hàng theo tên (có thể tìm tương đối, không phân biệt hoa thường)
    public void searchCustomerByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên khách hàng cần tìm: ");
        String searchName = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Customer c : cus) {
            if (c.getName().toLowerCase().contains(searchName)) {
                c.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy khách hàng nào có tên chứa: " + searchName);
        }
        sc.close(); // Xử lý cảnh báo
    }

    // Dữ liệu mẫu (nếu cần)
    public void themDuLieuMau() {
        cus.add(new Customer("C01", "Nguyen Van A", "a@gmail.com", "0123456789"));
        cus.add(new Customer("C02", "Tran Thi B", "b@gmail.com", "0223456789"));
        cus.add(new Customer("C03", "Le Van C", "c@gmail.com", "0323456789"));
    }
}
