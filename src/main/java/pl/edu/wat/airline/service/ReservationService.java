package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.repository.ReservationRepo;

import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }

    public Iterable<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }

}
