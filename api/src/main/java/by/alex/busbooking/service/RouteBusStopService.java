package by.alex.busbooking.service;

import by.alex.busbooking.dto.RouteBusStopDTO;

import java.util.List;

public interface RouteBusStopService {
    RouteBusStopDTO getRouteBusStop(int id);

    RouteBusStopDTO addRouteBusStop(RouteBusStopDTO routeBusStopDTO);

    List<RouteBusStopDTO> getRouteBusStops();

    List<RouteBusStopDTO> getRouteBusStopsByRouteId(int routeId);

    void deleteRouteBusStop(int id);
}
