package Port.In;

import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;
import exceptions.LoginInUseExceptionEnt;

import java.util.UUID;

public interface UserPortIn {

    User create(User user) throws LoginInUseException, LoginInUseExceptionEnt;

    User delete(UUID uuid);

    User update(User object) throws LoginInUseException, LoginInUseExceptionEnt;

    User activate(UUID uuid);

    User deactivate(UUID uuid);
}
