package by.alex.busbooking.controller;

import by.alex.busbooking.dto.DriverDTO;
import by.alex.busbooking.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/drivers")
@Tag(name = "Drivers")
public class DriverController {
    private final DriverService driverService;

    @Operation(summary = "Get all drivers")
    @ApiResponse(responseCode = "200", description = "Found the drivers",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DriverDTO.class))})
    @GetMapping("/")
    public List<DriverDTO> getDrivers() {
        return driverService.getDrivers();
    }

    @Operation(summary = "Get a driver by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the driver",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable int id) {
        return driverService.getDriver(id);
    }

    @Operation(summary = "Add new driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new driver",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DriverDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public DriverDTO addDriver(@Valid @RequestBody DriverDTO driverDTO) {
        return driverService.addDriver(driverDTO);
    }

    @Operation(summary = "Delete driver")
    @ApiResponse(responseCode = "200", description = "Deleted the driver")
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
    }
}
