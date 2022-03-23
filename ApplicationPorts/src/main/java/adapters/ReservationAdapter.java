package adapters;

import model.Reservation;
import modelEnt.ReservationEnt;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter {

    LaneAdapter laneAdapter = new LaneAdapter();
    UserAdapter userAdapter = new UserAdapter();

    public Reservation convertToReservation(ReservationEnt reservationEnt) {
        return new Reservation(reservationEnt.getUuid(), laneAdapter.convertToLane(reservationEnt.getLane()),
                userAdapter.convertToUser(reservationEnt.getClient()),
                reservationEnt.getStartReservation(), reservationEnt.getEndReservation());
    }

    public List<Reservation> convertToListReservation(List<ReservationEnt> reservationEntsList) {
        List<Reservation> convertedList = new ArrayList<>();
        for (ReservationEnt reservation : reservationEntsList) {
            convertedList.add(convertToReservation(reservation));
        }
        return convertedList;
    }

    public ReservationEnt convertFromReservation(Reservation reservation) {
        return new ReservationEnt(reservation.getUuid(), laneAdapter.convertFromLane(reservation.getLane()),
                userAdapter.convertFromUser(reservation.getUser()),
                reservation.getStartReservation(), reservation.getEndReservation());
    }

    public List<ReservationEnt> convertFromListReservation(List<Reservation> reservationList) {
        List<ReservationEnt> convertedList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            convertedList.add(convertFromReservation(reservation));
        }
        return convertedList;
    }

}
