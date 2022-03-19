package adapters;

import Port.UserPort;
import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;

import java.util.List;
import java.util.UUID;

public class UserPortAdapter implements UserPort {
    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User readById(UUID uuid) {
        return null;
    }

    @Override
    public User create(User object) throws LoginInUseException {
        return null;
    }

    @Override
    public User delete(UUID uuid) {
        return null;
    }

    @Override
    public User update(User object) throws LoginInUseException {
        return null;
    }
}
