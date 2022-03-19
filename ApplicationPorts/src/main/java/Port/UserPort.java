package Port;


import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;

import java.util.List;
import java.util.UUID;

public interface UserPort extends Port<User> {
    @Override
    List<User> readAll();

    @Override
    User readById(UUID uuid);

    @Override
    User create(User object) throws LoginInUseException;

    @Override
    User delete(UUID uuid);

    @Override
    User update(User object) throws LoginInUseException;
}
