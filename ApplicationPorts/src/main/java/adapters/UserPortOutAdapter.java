package adapters;

import Port.Out.UserPortOut;
import exceptions.LoginInUseExceptionEnt;
import model.User;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserPortOutAdapter implements UserPortOut {

    UserAdapter userAdapter = new UserAdapter();
    UserRepository userRepository = new UserRepository();

    public UserPortOutAdapter() throws LoginInUseExceptionEnt {
    }

    @Override
    public List<User> readAll() {
        return userAdapter.convertToListUser(userRepository.readAll());
    }

    @Override
    public User readById(UUID uuid) {
        return userAdapter.convertToUser(userRepository.readById(uuid));
    }

    @Override
    public List<User> readManyByLogin(String login) {
        return userAdapter.convertToListUser(userRepository.readManyByLogin(login));
    }
}
