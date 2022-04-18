package converters;

import model.Reservation;
import modelView.ReservationView;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Reservation> convertToListReservation(List<ReservationView> reservationViewsList) {
        List<Reservation> convertedList = new ArrayList<>();
        for (ReservationView reservation : reservationViewsList) {
            convertedList.add(convertToReservation(reservation));
        }
        return convertedList;
    }

    public static ReservationView convertFromReservation(Reservation reservation) {
        return new ReservationView(reservation.getUuid(), convertFromLane(reservation.getLane()),
                convertFromUser(reservation.getUser()),
                reservation.getStartReservation(), reservation.getEndReservation());
    }

    public static List<ReservationView> convertFromListReservation(List<Reservation> reservationList) {
        List<ReservationView> convertedList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            convertedList.add(convertFromReservation(reservation));
        }
        return convertedList;
    }

}
