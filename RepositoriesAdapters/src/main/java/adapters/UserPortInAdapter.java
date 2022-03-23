package adapters;

import Port.In.UserPortIn;
import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.User;
import exceptions.LoginInUseExceptionEnt;
import repository.UserRepository;

import java.util.UUID;

public class UserPortInAdapter implements UserPortIn {

    UserAdapter userAdapter = new UserAdapter();
    UserRepository userRepository = new UserRepository();

    public UserPortInAdapter() throws LoginInUseExceptionEnt {
    }

    @Override
    public User create(User user) throws LoginInUseException {
        try {
            return userAdapter.convertToUser(userRepository.create(userAdapter.convertFromUser(user)));
        } catch (LoginInUseExceptionEnt e) {
            throw new LoginInUseException(e.getMessage());
        }
    }

    @Override
    public User delete(UUID uuid) {
        return userAdapter.convertToUser(userRepository.delete(uuid));
    }

    @Override
    public User update(User object) throws LoginInUseException {
        try {
            return userAdapter.convertToUser(userRepository.update(userAdapter.convertFromUser(object)));
        } catch (LoginInUseExceptionEnt e) {
            throw new LoginInUseException(e.getMessage());
        }
    }

    @Override
    public User activate(UUID uuid) {
        return userAdapter.convertToUser(userRepository.activate(uuid));
    }

    @Override
    public User deactivate(UUID uuid) {
        return userAdapter.convertToUser(userRepository.deactivate(uuid));
    }
}
