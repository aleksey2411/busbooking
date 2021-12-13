package by.alex.busbooking.dto;

import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DriverDTO {
    @JsonView(View.User.class)
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(description = "Driver license", example = "4AE 012345")
    @NotBlank
    private String driverLicense;

    @JsonView(View.User.class)
    @Schema(description = "Name", example = "Ivan")
    @NotBlank
    private String firstName;

    @JsonView(View.User.class)
    @Schema(description = "Surname", example = "Ivanov")
    @NotBlank
    private String lastName;
}
