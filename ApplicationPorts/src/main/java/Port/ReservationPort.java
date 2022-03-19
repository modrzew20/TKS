package Port;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationPort extends Port<Reservation> {
    @Override
    List<Reservation> readAll();

    @Override
    Reservation readById(UUID uuid);

    @Override
    Reservation create(Reservation object) throws LoginInUseException;

    @Override
    Reservation delete(UUID uuid);

    @Override
    Reservation update(Reservation object) throws LoginInUseException;
}
