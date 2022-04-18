package converters;

import model.Reservation;
import modelEnt.ReservationEnt;

import java.util.ArrayList;
import java.util.List;

import static converters.LaneConverter.convertFromLane;
import static converters.LaneConverter.convertToLane;
import static converters.UserConverter.convertFromUser;
import static converters.UserConverter.convertToUser;

public class ReservationConverter {

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
