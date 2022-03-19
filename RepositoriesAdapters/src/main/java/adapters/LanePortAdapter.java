package adapters;

import Port.LanePort;
import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Lane;

import java.util.List;
import java.util.UUID;

public class LanePortAdapter implements LanePort {
    @Override
    public List<Lane> readAll() {
        return null;
    }

    @Override
    public Lane readById(UUID uuid) {
        return null;
    }

    @Override
    public Lane create(Lane object) throws LoginInUseException {
        return null;
    }

    @Override
    public Lane delete(UUID uuid) {
        return null;
    }

    @Override
    public Lane update(Lane object) throws LoginInUseException {
        return null;
    }
}
