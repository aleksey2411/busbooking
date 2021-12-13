package by.alex.busbooking.controller;

import by.alex.busbooking.dto.TripDTO;
import by.alex.busbooking.service.TripService;
import by.alex.busbooking.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trips")
@Tag(name = "Trips")
public class TripController {
    private final TripService tripService;

    @JsonView(View.User.class)
    @Operation(summary = "Get trips by route id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the trips by route id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TripDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Route not found",
                    content = @Content)})
    @GetMapping("/routes/{routeId}/{date}")
    public List<TripDTO> getTripsByRoute(@PathVariable int routeId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date) {
        return tripService.getTripsByRouteAndDate(routeId, date);
    }


    @JsonView(View.User.class)
    @Operation(summary = "Get all trips")
    @ApiResponse(responseCode = "200", description = "Found the trips",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TripDTO.class))})
    @GetMapping("/")
    public List<TripDTO> getTrips() {
        return tripService.getTrips();
    }

    @JsonView(View.User.class)
    @Operation(summary = "Get a trip by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TripDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public TripDTO getTrip(@PathVariable int id) {
        return tripService.getTrip(id);
    }

    @Operation(summary = "Add new trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TripDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public TripDTO addTrip(@Valid @RequestBody TripDTO tripDTO) {
        return tripService.addTrip(tripDTO);
    }

    @Operation(summary = "Delete trip")
    @ApiResponse(responseCode = "200", description = "Deleted the trip")
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
    }
}
