package aggregates;

import model.Reservation;
import modelEnt.ReservationEnt;
import java.util.ArrayList;
import java.util.List;
import static aggregates.LaneAdapter.convertFromLane;
import static aggregates.LaneAdapter.convertToLane;
import static aggregates.UserAdapter.convertFromUser;
import static aggregates.UserAdapter.convertToUser;

public class ReservationAdapter {

    public static Reservation convertToReservation(ReservationEnt reservationEnt) {
        return new Reservation(reservationEnt.getUuid(), convertToLane(reservationEnt.getLane()),
                convertToUser(reservationEnt.getClient()),
                reservationEnt.getStartReservation(), reservationEnt.getEndReservation());
    }

    public static List<Reservation> convertToListReservation(List<ReservationEnt> reservationEntsList) {
        List<Reservation> convertedList = new ArrayList<>();
        for (ReservationEnt reservation : reservationEntsList) {
            convertedList.add(convertToReservation(reservation));
        }
        return convertedList;
    }

    public static ReservationEnt convertFromReservation(Reservation reservation) {
        return new ReservationEnt(reservation.getUuid(), convertFromLane(reservation.getLane()),
                convertFromUser(reservation.getUser()),
                reservation.getStartReservation(), reservation.getEndReservation());
    }

    public static List<ReservationEnt> convertFromListReservation(List<Reservation> reservationList) {
        List<ReservationEnt> convertedList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            convertedList.add(convertFromReservation(reservation));
        }
        return convertedList;
    }

}
