package by.alex.busbooking.service;

import by.alex.busbooking.dto.TripDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
    TripDTO getTrip(int id);

    TripDTO addTrip(TripDTO tripDTO);

    List<TripDTO> getTrips();

    List<TripDTO> getTripsByRouteAndDate(int routeId, LocalDate date);

    void deleteTrip(int id);
}
