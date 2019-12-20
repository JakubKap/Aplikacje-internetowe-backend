package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
    @Query("SELECT new pl.edu.wat.airline.entities.Reservation(r.id, r.reservationNo, r.isReservationPaid, r.isOnlineCheckInMade, r.numOfAdults, r.numOfInfants, r.numOfChildren, r.travelClass, r.reservationPrice, r.flight, r.user) " +
            "FROM Reservation r " +
            "WHERE r.user.id = :userId"
    )
    List<Reservation> findByUserId(@Param("userId") Long userId);

    Optional<Reservation> findByReservationNo(@Param("reservationNo") String reservationNo);
}
