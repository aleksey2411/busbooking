package by.alex.busbooking.repository;

import by.alex.busbooking.entity.RouteBusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteBusStopRepository extends JpaRepository<RouteBusStop, Integer> {
    List<RouteBusStop> getAllByRouteId(int routeId);
}
