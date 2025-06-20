package com.example.servingwebcontent;
import com.example.servingwebcontent.model.*;

import java.util.Scanner;


public class MainMenu {
    private MovieList movieList = new MovieList();
    private CustomerList customerList = new CustomerList();
    private TicketList ticketList = new TicketList();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Quản lý phim");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý vé");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    movieMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    ticketMenu();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private void movieMenu() {
        try {
            System.out.println("\n--- Quản lý phim ---");
            System.out.println("1. Thêm phim");
            System.out.println("2. Hiển thị danh sách phim");
            System.out.println("3. Xoá phim");
            System.out.print("Chọn: ");
            int c = Integer.parseInt(sc.nextLine());
            if (c == 1) {
                System.out.print("ID phim: ");
                String id = sc.nextLine();
                System.out.print("Tên phim: ");
                String name = sc.nextLine();
                System.out.print("Tên phim: ");
                String title = sc.nextLine();
                System.out.print("Ngày chiếu: ");
                String showTime = sc.nextLine();
                System.out.print("Giờ chiếu: ");
                String dateTime = sc.nextLine();
                System.out.print("Thời lượng: ");
                int duration = Integer.parseInt(sc.nextLine());
                System.out.print("Thể loại: ");
                String genre = sc.nextLine();
                System.out.print("Độ tuổi (chỉ nhập số): ");
                int age = Integer.parseInt(sc.nextLine());
                Movie m = new Movie(id, name, title, showTime, dateTime ,duration, genre, age);
                movieList.addMovie(m);
                System.out.println("Đã thêm phim.");
            } else if (c == 2) {
                movieList.printMovieList();
            } else if (c == 3) {
                System.out.print("Nhập ID phim cần xoá: ");
                String movieIdStr = sc.nextLine();
                int movieId = Integer.parseInt(movieIdStr);
                movieList.getDeleteMovie(movieId);
                System.out.println("Đã xoá phim.");
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Kết thúc quản lý phim.");
        }
    }

    private void customerMenu() {
        System.out.println("\n--- Quản lý khách hàng ---");
        System.out.println("1. Thêm khách hàng");
        System.out.println("2. Hiển thị danh sách khách hàng");
        System.out.println("3. Xoá khách hàng");
        System.out.print("Chọn: ");
        int c = Integer.parseInt(sc.nextLine());
        if (c == 1) {
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Tên: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Số điện thoại: ");
            String phone = sc.nextLine();
            Customer cu = new Customer(id, name, email, phone);
            customerList.addCustomer(cu);
            System.out.println("Đã thêm khách hàng.");
        } else if (c == 2) {
            customerList.printCustomerList();
        } else if (c == 3) {
            System.out.print("Nhập ID khách hàng cần xoá: ");
            String cusId = sc.nextLine();
            customerList.deleteCustomer(cusId);
            System.out.println("Đã xoá khách hàng.");
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void ticketMenu() {
        System.out.println("\n--- Quản lý vé ---");
        System.out.println("1. Thêm vé");
        System.out.println("2. Hiển thị danh sách vé");
        System.out.println("3. Xoá vé");
        System.out.print("Chọn: ");
        int c = Integer.parseInt(sc.nextLine());
        if (c == 1) {
               System.out.print("ID vé: ");
                String id = sc.nextLine();

                // Chọn phim cho vé
                System.out.println("Danh sách phim:");
                movieList.printMovieList();
                System.out.print("Nhập ID phim: ");
                String movieId = sc.nextLine();
                Movie movie = movieList.findMovieById(movieId);
                if (movie == null) {
                    System.out.println("Không tìm thấy phim!");
                    return;
                }

                System.out.print("Số ghế: ");
                String seat = sc.nextLine();
                System.out.print("Giá vé: ");
                double price = Double.parseDouble(sc.nextLine());

                Ticket ticket = new Ticket(id, movie, seat, price);
                ticketList.addTicket(ticket);
                System.out.println("Đã thêm vé.");
            System.out.println("Đã thêm vé.");
        } else if (c == 2) {
            ticketList.printTicketList();
        } else if (c == 3) {
            System.out.print("Nhập ID vé cần xoá: ");
            String ticketId = sc.nextLine();
            Ticket ticket = ticketList.findTicketById(ticketId);
            if (ticket == null) {
                System.out.println("Không tìm thấy vé với ID: " + ticketId);
                return;
            }
            ticketList.updateTicketById(ticketId, null); // Xoá vé bằng cách
            System.out.println("Xoá vé thành công.");
            ticketList.getAllTickets().remove(ticket); // Xoá vé khỏi danh sách
            ticketList.removeTicketById(ticketId); // Gọi phương thức xoá vé
            ticketList.printTicketsByCustomerId(ticketId); // In danh sách vé sau khi

            System.out.println("Đã xoá vé.");
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
        }

    }
}
