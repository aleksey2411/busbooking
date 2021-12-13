package by.alex.busbooking.dto;

import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RouteDTO {
    @JsonView(View.User.class)
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @JsonView(View.User.class)
    @Schema(description = "Arrival city")
    @NotNull
    private CityDTO arrivalCityDTO;

    @JsonView(View.User.class)
    @Schema(description = "Departure city")
    @NotNull
    private CityDTO departureCityDTO;

    @JsonView(View.User.class)
    @Schema(description = "Cost")
    @NotNull
    private int cost;
}
