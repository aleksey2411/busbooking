package by.alex.busbooking.dto;

import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BusDTO {
    @JsonView(View.User.class)
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @JsonView(View.User.class)
    @Schema(description = "Bus number",example = "AM0224-4")
    @NotBlank
    private String number;

    @JsonView(View.User.class)
    @Schema(description = "Mark",example = "BMW")
    @NotBlank
    private String mark;

    @JsonView(View.User.class)
    @Schema(description = "Model",example = "X5")
    @NotBlank
    private String model;

    @JsonView(View.User.class)
    @Schema(description = "Color",example = "Black")
    @NotBlank
    private String color;

    @JsonView(View.User.class)
    @Schema(description = "Number of seats")
    @NotNull
    private int numberOfSeats;
}
