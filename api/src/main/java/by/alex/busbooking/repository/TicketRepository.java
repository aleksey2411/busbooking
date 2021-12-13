package by.alex.busbooking.repository;

import by.alex.busbooking.dto.TicketDTO;
import by.alex.busbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "select ifnull((b.number_of_seats - sum(t.number_of_seats)),b.number_of_seats) from ticket t " +
            "right join trip tr on tr.trip_id = t.trip_id " +
            "join bus b on b.bus_id = tr.bus_id " +
            "where tr.trip_id=:tripId", nativeQuery = true)
    int getNumberOfFreSeats(@Param("tripId") int tripId);

    List<Ticket> getAllByUserId(int userId);

    List<Ticket> getAllByDepartureDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
