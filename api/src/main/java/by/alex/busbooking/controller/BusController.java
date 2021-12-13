package by.alex.busbooking.controller;

import by.alex.busbooking.dto.BusDTO;
import by.alex.busbooking.service.BusService;
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
@RequestMapping("/api/buses")
@Tag(name = "Buses")
public class BusController {
    private final BusService busService;

    @Operation(summary = "Get all buses")
    @ApiResponse(responseCode = "200", description = "Found the buses",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BusDTO.class))})
    @GetMapping("/")
    public List<BusDTO> getBuses() {
        return busService.getBuses();
    }

    @Operation(summary = "Get a bus by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public BusDTO getBus(@PathVariable int id) {
        return busService.getBus(id);
    }

    @Operation(summary = "Add new bus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new bus",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public BusDTO addBus(@Valid @RequestBody BusDTO busDTO) {
        return busService.addBus(busDTO);
    }

    @Operation(summary = "Delete bus")
    @ApiResponse(responseCode = "200", description = "Deleted the bus")
    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable int id) {
        busService.deleteBus(id);
    }
}
