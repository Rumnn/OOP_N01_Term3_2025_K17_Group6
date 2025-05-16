package model;

public class Ticket {
    private String ticketId; //mã vé
    private Movie movie;//phim (gồm tên, thể loại, thời lượng)
    private String seat; //hàng ghế
    private String showTime;// thời gian chiếu 
    private double price;// giá 

    public Ticket(String ticketId, Movie movie, String seat, String showTime, double price) {
        this.ticketId = ticketId;
        this.movie = movie;
        this.seat = seat;
        this.showTime = showTime;
        this.price = price;
    }

    public void printTicket() {
        System.out.println("=== Movie Ticket ===");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Movie       : " + movie.getTitle());
        System.out.println("GenreGenre  : " + movie.getGenre());
        System.out.println("Duration    : " + movie.getDuration() + " phút");
        System.out.println("Launch Date : " + showTime);
        System.out.println("Seat        : " + seat);
        System.out.println("Price       : " + price + " VND");
    }
}
