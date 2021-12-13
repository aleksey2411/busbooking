package by.alex.busbooking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int id;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
