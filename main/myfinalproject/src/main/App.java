package main;

public class App {
    public static void main(String[] args) {
        System.out.println("Chương trình quản lý rạp chiếu phim đã được khởi động.");

        MainMenu menu = new MainMenu();
        menu.run();
    }
}