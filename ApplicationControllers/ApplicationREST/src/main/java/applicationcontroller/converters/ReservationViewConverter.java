package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.ReservationView;
import model.Reservation;


public class ReservationViewConverter {

    public static Reservation convertToReservation(ReservationView reservationView) {
        return new Reservation(reservationView.getUuid(), LaneViewConverter.convertToLane(reservationView.getLane()),
                UserViewConverter.convertToUser(reservationView.getUser()),
                reservationView.getStartReservation(), reservationView.getEndReservation());
    }

    public static ReservationView convertFromReservation(Reservation reservation) {
        return new ReservationView(reservation.getUuid(), LaneViewConverter.convertFromLane(reservation.getLane()),
                UserViewConverter.convertFromUser(reservation.getUser()),
                reservation.getStartReservation(), reservation.getEndReservation());
    }
}
