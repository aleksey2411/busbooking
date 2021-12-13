package by.alex.busbooking.dto;

import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityDTO {
    @JsonView(View.User.class)
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @JsonView(View.User.class)
    @Schema(description = "City")
    @NotBlank
    private String name;
}
