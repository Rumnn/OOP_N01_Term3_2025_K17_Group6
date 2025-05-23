 package test;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;

public class TestCustomer {
    public 
    public static void test(String[] args) {
        Customer c = new Customer("KH01", "Nguyen Van A", "a@gmail.com", "0901234567");
        c.displayInfo(); // Kỳ vọng: In ra tên, email và số điện thoại khách
    }
    public void testEditDelete() {

        ArrayList<Customer> sl = new ArrayList<Customer>();
        Customer s1 = new Customer("KH01", "Nguyen Van A",,"a@gmail.com", 12345);
        Customer s2 = new Customer("Tran Van Minh", 2);
        Customer s3 = new Customer("Nguyen An", 101010);

        sl.add(s1);
        sl.add(s2);
        sl.add(s3);

        CustomerList stuList = new CustomerList();
        stuList.addCustomers(s1);
        stuList.addCustomers(s2);
        stuList.addCustomers(s3);

        // cap nhat thong tin

        System.out.println("Enter Customers ID");
        Scanner CustomersID = new Scanner(System.in);

        int s = CustomersID.nextInt();

        System.out.println("Enter Customers fullname");

        Scanner fullname = new Scanner(System.in); // Create a Scanner object

        String newName = fullname.nextLine();

        stuList.getEditCustomers(newName, s);

        stuList.printCustomersList();

        System.out.print("test xoa:");

       
        System.out.println("Enter Customers ID");
        Scanner ID = new Scanner(System.in);

        int CustomersDel = ID.nextInt();
        stuList.getDeleteCustomers(CustomersDel);
        System.out.print("danh sach sau khi xoa:");
        stuList.printCustomersList();
}
