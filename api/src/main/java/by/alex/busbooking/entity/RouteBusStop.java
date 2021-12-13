package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "route_bus_stop")
public class RouteBusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_bus_stop_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bus_stop_id")
    private BusStop busStop;

    @Column(name = "time_to_bus_stop_in_minutes")
    private int timeToBusStopInMinutes;
}
