  package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerList {

    ArrayList<Customer> cus = new ArrayList<Customer>();


    public ArrayList<Customer> addCustomers(Customer cu) {

        cus.add(cu);
        return cus;

    }

    public ArrayList<Customer> getEditCustomers(String fullname, int cusId) {

        for (int i = 0; i < cus.size(); i++) {
            if (Integer.parseInt(cus.get(i).getId()) == cusId) {
                cus.get(i).setName(fullname); // Cập nhật tên khách hàng
                break;
            }
        }
        return cus; // Trả về danh sách vé
    
    
    }
    public ArrayList<Customer> getDeleteCustomers(int cusId) {

        for (int i = 0; i < cus.size(); i++) {
            if (Integer.parseInt(cus.get(i).getId()) == cusId) {
                cus.remove(i); // Xóa khách hàng
                break;
            }
        }
        return cus; // Trả về danh sách vé
    
    
    }


    public void printCustomerList() {
        int len= cus.size();
        for (int i=0; i < len; i++) {
                System.out.println("Customer ID: " + cus.get(i).getId());
                System.out.println("Customer Name: " + cus.get(i).getName());
        }
    }



    
}
