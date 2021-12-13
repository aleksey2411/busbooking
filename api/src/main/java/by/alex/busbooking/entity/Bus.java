package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "number_of_seats")
    private int numberOfSeats;
}
