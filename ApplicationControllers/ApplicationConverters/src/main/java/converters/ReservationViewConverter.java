package converters;

import model.Reservation;
import modelView.ReservationView;

import static converters.LaneViewConverter.convertFromLane;
import static converters.LaneViewConverter.convertToLane;
import static converters.UserViewConverter.convertFromUser;
import static converters.UserViewConverter.convertToUser;

public class ReservationViewConverter {

    public static Reservation convertToReservation(ReservationView reservationView) {
        return new Reservation(reservationView.getUuid(), convertToLane(reservationView.getLane()),
                convertToUser(reservationView.getUser()),
                reservationView.getStartReservation(), reservationView.getEndReservation());
    }

    public static ReservationView convertFromReservation(Reservation reservation) {
        return new ReservationView(reservation.getUuid(), convertFromLane(reservation.getLane()),
                convertFromUser(reservation.getUser()),
                reservation.getStartReservation(), reservation.getEndReservation());
    }
}
