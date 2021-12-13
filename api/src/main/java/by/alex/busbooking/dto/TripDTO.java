package by.alex.busbooking.dto;

import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TripDTO {
    @JsonView(View.User.class)
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @JsonView(View.User.class)
    @Schema(description = "Date/Time")
    @NotNull
    private LocalDateTime departureDateTime;

    @JsonView(View.User.class)
    @Schema(description = "Date/Time")
    @NotNull
    private LocalDateTime arrivalDateTime;


    @JsonView(View.User.class)
    @Schema(description = "Route")
    @NotNull
    private RouteDTO routeDTO;

    @JsonView(View.User.class)
    @Schema(description = "Bus")
    @NotNull
    private BusDTO busDTO;

    @JsonView(View.User.class)
    @Schema(description = "Driver")
    @NotNull
    private DriverDTO driverDTO;

    @JsonView(View.User.class)
    private int numberOfFreeSeats;
}
