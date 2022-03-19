package adapters;

import Port.ReservationPort;
import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Reservation;

import java.util.List;
import java.util.UUID;

public class ReservationPortAdapter implements ReservationPort {
    @Override
    public List<Reservation> readAll() {
        return null;
    }

    @Override
    public Reservation readById(UUID uuid) {
        return null;
    }

    @Override
    public Reservation create(Reservation object) throws LoginInUseException {
        return null;
    }

    @Override
    public Reservation delete(UUID uuid) {
        return null;
    }

    @Override
    public Reservation update(Reservation object) throws LoginInUseException {
        return null;
    }
}
