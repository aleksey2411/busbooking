package by.alex.busbooking.repository;

import by.alex.busbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> getTripsByRouteIdAndDepartureDateTimeBetween(int routeId, LocalDateTime startDate, LocalDateTime endDate);
    List<Trip> getTripsByDepartureDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "select ifnull((b.number_of_seats - sum(t.number_of_seats)),b.number_of_seats) from ticket t " +
            "right join trip tr on tr.trip_id = t.trip_id " +
            "join bus b on b.bus_id = tr.bus_id " +
            "where tr.trip_id=:tripId", nativeQuery = true)
    int getNumberOfFreSeats(@Param("tripId") int tripId);
}
