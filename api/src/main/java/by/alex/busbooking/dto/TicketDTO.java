package by.alex.busbooking.dto;

import by.alex.busbooking.entity.TicketStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TicketDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Date/Time of order", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime orderDateTime;

    @Schema(description = "Date/Time of departure", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime departureDateTime;

    @Schema(description = "Number of seats")
    @NotNull
    private int numberOfSeats;

    @Schema(description = "User")
    private UserDTO userDTO;

    @Schema(description = "Trip")
    @NotNull
    private TripDTO tripDTO;

    @Schema(description = "Bus stop of route")
    @NotNull
    private RouteBusStopDTO routeBusStopDTO;

    private TicketStatus ticketStatus;
}
