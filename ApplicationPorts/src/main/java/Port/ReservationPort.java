package Port;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationPort {

    List<Reservation> readAll();

    Reservation readById(UUID uuid);

    Reservation create(Reservation object);

    Reservation delete(UUID uuid);

    Reservation update(Reservation object);
}
