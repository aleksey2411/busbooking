package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @Column(name = "cost")
    private int cost;
}
