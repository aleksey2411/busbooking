package by.alex.busbooking.service;

import by.alex.busbooking.dto.BusStopDTO;

import java.util.List;

public interface BusStopService {
    BusStopDTO getBusStop(int id);

    BusStopDTO addBusStop(BusStopDTO busStopDTO);

    List<BusStopDTO> getBusStops();

    void deleteBusStop(int id);
}
