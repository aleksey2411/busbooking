package by.alex.busbooking.controller;

import by.alex.busbooking.service.BusStopService;
import by.alex.busbooking.service.CityService;
import by.alex.busbooking.dto.BusStopDTO;
import by.alex.busbooking.dto.CityDTO;
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
@RequestMapping("/api/cities")
@Tag(name = "Cities and bus stops")
public class CityController {
    private final CityService cityService;
    private final BusStopService busStopService;

    @Operation(summary = "Get all cities")
    @ApiResponse(responseCode = "200", description = "Found the cities",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CityDTO.class))})
    @GetMapping("/")
    public List<CityDTO> getCities() {
        return cityService.getCities();
    }

    @Operation(summary = "Get a city by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the city",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public CityDTO getCity(@PathVariable int id) {
        return cityService.getCity(id);
    }

    @Operation(summary = "Add new city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new city",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public CityDTO addCity(@Valid @RequestBody CityDTO cityDTO) {
        return cityService.addCity(cityDTO);
    }

    @Operation(summary = "Delete city")
    @ApiResponse(responseCode = "200", description = "Deleted the city")
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
    }

    @Operation(summary = "Get all bus stops")
    @ApiResponse(responseCode = "200", description = "Found the bus stops",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BusStopDTO.class))})
    @GetMapping("/bus-stops")
    public List<BusStopDTO> getBusStops() {
        return busStopService.getBusStops();
    }

    @Operation(summary = "Get a bus stop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bus stop",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusStopDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/bus-stops/{id}")
    public BusStopDTO getBusStop(@PathVariable int id) {
        return busStopService.getBusStop(id);
    }

    @Operation(summary = "Add new bus stop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new bus stop",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusStopDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/bus-stops")
    public BusStopDTO addBusStop(@Valid @RequestBody BusStopDTO busStopDTO) {
        return busStopService.addBusStop(busStopDTO);
    }

    @Operation(summary = "Delete bus stop")
    @ApiResponse(responseCode = "200", description = "Deleted the bus stop")
    @DeleteMapping("/bus-stops/{id}")
    public void deleteBusStop(@PathVariable int id) {
        busStopService.deleteBusStop(id);
    }
}
