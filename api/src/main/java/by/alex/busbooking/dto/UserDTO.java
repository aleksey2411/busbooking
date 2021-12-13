package by.alex.busbooking.dto;

import by.alex.busbooking.validation.FieldsValueMatch;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@FieldsValueMatch(field = "password", fieldMatch = "matchingPassword", message = "Password mismatch")
public class UserDTO {
    @Schema(description = "ID")
    private int id;

    @Length(min = 5)
    @NotBlank
    private String password;

    private String matchingPassword;

    @Schema(description = "Email", example = "alex@mail.ru")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Name", example = "Ivan")
    @NotBlank
    private String firstName;

    @Schema(description = "Surname", example = "Ivanov")
    @NotBlank
    private String lastName;

    private String role;
}
