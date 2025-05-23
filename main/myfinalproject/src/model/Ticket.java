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

    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }
    public String getShowTime() {
        return showTime;
    }
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    

    public void displayTicket() {
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
