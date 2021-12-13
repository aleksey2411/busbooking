package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;

    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "route_bus_stop_id")
    private RouteBusStop routeBusStop;

    @Column(name = "status")
    private TicketStatus ticketStatus;
}
