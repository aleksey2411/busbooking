package by.alex.busbooking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RouteBusStopDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Route")
    @NotNull
    private RouteDTO routeDTO;

    @Schema(description = "Bus stop")
    @NotNull
    private BusStopDTO busStopDTO;

    @Schema(description = "Time to bus stop in minutes")
    @NotNull
    private int timeToBusStopInMinutes;
}
