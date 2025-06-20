package com.example.servingwebcontent.model;

public class Ticket extends ObjectGeneral {
    // private String ticketId; //mã vé
    private Movie movie;//phim (gồm tên, thể loại, thời lượng)
    private String seat; //hàng ghế
    private double price;// giá

    public Ticket(String id, /*String name, String ticketId,*/ Movie movie, String seat, String time, double price) {
        super(id, ""); // Gọi constructor của ObjectGeneral với id và name rỗng
        // this.ticketId = ticketId;
        this.movie = movie;
        this.seat = seat;
        this.price = price;
    }

    public String getId() {
        return super.getId();
    }
    public void setId(String id) {
        super.setId(id);
    }

    // public String getTicketId() {
    //     return ticketId;
    // }
    // public void setTicketId(String ticketId) {
    //     this.ticketId = ticketId;
    // }
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    

    public void displayTicket() {
        System.out.println("=== Movie Ticket ===");
        System.out.println("Ticket ID   : " + getId());
        System.out.println("Movie       : " + movie.getName());
        System.out.println("GenreGenre  : " + movie.getGenre());
        System.out.println("Age    : " + movie.getAge() + " +");
        System.out.println("ShowTime : " + movie.getShowTime());
        System.out.println("DateTime   : " + movie.getDateTime());
        System.out.println("Seat        : " + seat);
        System.out.println("Price       : " + price + " VND");
    }
}
