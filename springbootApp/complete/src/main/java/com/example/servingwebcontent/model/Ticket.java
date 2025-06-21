package com.example.servingwebcontent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket extends ObjectGeneral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;//phim (gồm tên, thể loại, thời lượng)
    
    @Column(name = "seat")
    private String seat; //hàng ghế
    
    @Column(name = "price")
    private double price;// giá

    public Ticket() {
        super("", ""); // Gọi constructor của ObjectGeneral với id và name rỗng
        // this.ticketId = "";
        this.movie = new Movie(); // Khởi tạo đối tượng Movie mặc định
        this.seat = "";
        this.price = 0.0;
    }

    public Ticket(String id, Movie movie, String seat, double price) {
        super(id, ""); // Gọi constructor của ObjectGeneral với id và name rỗng
        // this.ticketId = ticketId;
        this.movie = movie;
        this.seat = seat;
        this.price = price;
    }

    public Long getDbId() {
        return dbId;
    }
    
    public void setDbId(Long dbId) {
        this.dbId = dbId;
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
