package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private int id;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
