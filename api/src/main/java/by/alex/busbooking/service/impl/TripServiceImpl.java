package by.alex.busbooking.service.impl;

import by.alex.busbooking.mapper.TripMapper;
import by.alex.busbooking.repository.TripRepository;
import by.alex.busbooking.service.TripService;
import by.alex.busbooking.dto.TripDTO;
import by.alex.busbooking.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    @Override
    public TripDTO getTrip(int id) {
        Trip trip = tripRepository.getById(id);
        return tripMapper.tripToTripDTO(trip);
    }

    @Override
    public TripDTO addTrip(TripDTO tripDTO) {
        Trip trip = tripMapper.tripDTOtoTrip(tripDTO);
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.tripToTripDTO(savedTrip);
    }

    @Override
    public List<TripDTO> getTrips() {
        LocalDate date = LocalDate.now();
        final LocalDateTime startDate = date.atStartOfDay();
        final LocalDateTime endDate = date.atTime(LocalTime.MAX);
        List<Trip> trips = tripRepository.getTripsByDepartureDateTimeBetween(startDate, endDate);
        return tripMapper.tripToTripDTO(trips);
    }


    @Override
    public List<TripDTO> getTripsByRouteAndDate(int routeId, LocalDate date) {
        final LocalDateTime startDate = date.atStartOfDay();
        final LocalDateTime endDate = date.atTime(LocalTime.MAX);
        List<Trip> trips = tripRepository.getTripsByRouteIdAndDepartureDateTimeBetween(routeId,startDate,endDate);
        List<TripDTO> tripDTOS = tripMapper.tripToTripDTO(trips);
        tripDTOS.forEach(tripDTO -> {
            tripDTO.setNumberOfFreeSeats(tripRepository.getNumberOfFreSeats(tripDTO.getId()));
        });

        return tripDTOS;
    }

    @Override
    public void deleteTrip(int id) {
        tripRepository.deleteById(id);
    }
}
