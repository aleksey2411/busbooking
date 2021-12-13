package by.alex.busbooking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BusStopDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Bus stop")
    @NotBlank
    private String name;

    @Schema(description = "City")
    @NotNull
    private CityDTO cityDTO;
}
