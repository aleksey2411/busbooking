package by.alex.busbooking.controller;

import by.alex.busbooking.dto.UserDTO;
import by.alex.busbooking.service.UserService;
import by.alex.busbooking.validation.user.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @InitBinder("userDto")
    protected void userValidator(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Found the users",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))})
    @GetMapping("/")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Get most active user in the last month by bus id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the most active user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Bus not found",
                    content = @Content)})
    @GetMapping("/most-active/{busId}")
    public List<Object> getMostActiveUsersLastMonth(@PathVariable int busId) {
        return userService.getMostActiveUserLastMonth(busId);
    }

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PutMapping("/")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @Operation(summary = "Delete user")
    @ApiResponse(responseCode = "200", description = "Deleted the user")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
