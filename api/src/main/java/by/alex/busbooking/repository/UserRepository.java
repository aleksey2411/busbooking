package by.alex.busbooking.repository;

import by.alex.busbooking.entity.Role;
import by.alex.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user u " +
            "join ticket t on u.user_id = t.user_id " +
            "join trip tr on t.trip_id = tr.trip_id " +
            "join bus b on b.bus_id = tr.bus_id " +
            "where b.bus_id=:busId and t.departure_date_time between :startDate and :endDate " +
            "group by u.user_id " +
            "order by count(u.user_id) desc limit 1", nativeQuery = true)
    User getMostActiveUserLastMonthByBusId(@Param("busId") int busId,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);

    User findByEmail(String email);
}