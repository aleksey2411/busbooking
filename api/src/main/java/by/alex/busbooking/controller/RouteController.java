package by.alex.busbooking.controller;

import by.alex.busbooking.dto.RouteBusStopDTO;
import by.alex.busbooking.dto.RouteDTO;
import by.alex.busbooking.service.RouteBusStopService;
import by.alex.busbooking.service.RouteService;
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
@RequestMapping("/api/routes")
@Tag(name = "Routes")
public class RouteController {
    private final RouteService routeService;
    private final RouteBusStopService routeBusStopService;

    @Operation(summary = "Get all routes")
    @ApiResponse(responseCode = "200", description = "Found the routes",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RouteDTO.class))})
    @GetMapping("/")
    public List<RouteDTO> getRoutes() {
        return routeService.getRoutes();
    }

    @Operation(summary = "Get a route by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the route",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RouteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @GetMapping("/{id}")
    public RouteDTO getRoute(@PathVariable int id) {
        return routeService.getRoute(id);
    }

    @GetMapping("/{departureCityId}/{arrivalCityId}")
    public RouteDTO getRouteByCities(@PathVariable int arrivalCityId, @PathVariable int departureCityId) {
        return routeService.getRouteByCities(arrivalCityId, departureCityId);
    }

    @Operation(summary = "Add new route")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new route",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RouteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/")
    public RouteDTO addRoute(@Valid @RequestBody RouteDTO routeDTO) {
        return routeService.addRoute(routeDTO);
    }

    @Operation(summary = "Delete route")
    @ApiResponse(responseCode = "200", description = "Deleted the route")
    @DeleteMapping("{id}")
    public void deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
    }

    @Operation(summary = "Get all route bus stops")
    @ApiResponse(responseCode = "200", description = "Found the route bus stops",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RouteDTO.class))})
    @GetMapping("/bus-stops")
    public List<RouteBusStopDTO> getRouteBusStops() {
        return routeBusStopService.getRouteBusStops();
    }

    @GetMapping("/bus-stops/{routeId}")
    public List<RouteBusStopDTO> getRouteBusStopsByRouteId(@PathVariable int routeId) {
        return routeBusStopService.getRouteBusStopsByRouteId(routeId);
    }

    @Operation(summary = "Add new route bus stop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new route bus stop",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RouteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid validation",
                    content = @Content)})
    @PostMapping("/bus-stops")
    public RouteBusStopDTO addRouteBusStop(@Valid @RequestBody RouteBusStopDTO routeBusStopDTO) {
        return routeBusStopService.addRouteBusStop(routeBusStopDTO);
    }

    @Operation(summary = "Delete route bus stop")
    @ApiResponse(responseCode = "200", description = "Deleted the route bus stop")
    @DeleteMapping("/bus-stops/{id}")
    public void deleteRouteBusStop(@PathVariable int id) {
        routeBusStopService.deleteRouteBusStop(id);
    }
}
