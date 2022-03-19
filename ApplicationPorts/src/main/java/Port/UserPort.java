package Port;


import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;

import java.util.List;
import java.util.UUID;

public interface UserPort {

    List<User> readAll();

    User readById(UUID uuid);

    User create(User object) throws LoginInUseException;

    User delete(UUID uuid);

    User update(User object) throws LoginInUseException;
}
