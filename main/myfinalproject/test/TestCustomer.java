 package test;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;
import model.CustomerList;

public class TestCustomer {

    public void testEditCustomer() {

        ArrayList<Customer> cl = new ArrayList<Customer>();
        Customer s1 = new Customer("KH02", "Nguyen Van Ann","ann@gmail.com", "0651234567");
        Customer s2 = new Customer("KH03", "Tran Van Minh", "tran@gmail.com", "0657654321");
        Customer s3 = new Customer("KH04", "Nguyen Anh", "nguyen@gmail.com", "0123456789");

        cl.add(s1);
        cl.add(s2);
        cl.add(s3);

        CustomerList CusList = new CustomerList();
        CusList.addCustomers(s1);
        CusList.addCustomers(s2);
        CusList.addCustomers(s3);

        // cap nhat thong tin

        System.out.println("nhap ID khach hang can sua");
        Scanner CustomersID = new Scanner(System.in);

        String s = CustomersID.nextLine();

        System.out.println("nhap ten khach hang moi");

        Scanner fullname = new Scanner(System.in); // Create a Scanner object

        String newName = fullname.nextLine();

        CusList.getEditCustomers(newName, s);

        CusList.printCustomerList();

        System.out.println("test xoa:");
       
        System.out.println("nhap ID khach hang can xoa");
        Scanner ID = new Scanner(System.in);

        String CustomersDel = ID.nextLine();
        CusList.getDeleteCustomers(CustomersDel);
        System.out.print("danh sach sau khi xoa:");
        CusList.printCustomerList();
        System.out.println("test xoa thanh cong");
    }
    public static void main(String[] args) {
        TestCustomer ts = new TestCustomer();
        ts.testEditCustomer();
        System.out.println("test thanh cong");

    }
}
