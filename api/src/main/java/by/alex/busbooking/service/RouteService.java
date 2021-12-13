package by.alex.busbooking.service;

import by.alex.busbooking.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    RouteDTO getRoute(int id);

    RouteDTO addRoute(RouteDTO routeDTO);

    RouteDTO getRouteByCities(int arrivalCityId, int departureCityId);

    List<RouteDTO> getRoutes();

    void deleteRoute(int id);
}
