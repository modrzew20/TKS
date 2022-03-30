package adapters;

import Port.Out.ReadUserPort;
import exceptions.LoginInUseExceptionEnt;
import model.User;
import repository.UserRepository;
import java.util.List;
import java.util.UUID;
import static aggregates.UserAdapter.convertToListUser;
import static aggregates.UserAdapter.convertToUser;

public class UserPortOutAdapter implements ReadUserPort {

    UserRepository userRepository = new UserRepository();

    public UserPortOutAdapter() throws LoginInUseExceptionEnt {
    }

    @Override
    public List<User> readAll() {
        return convertToListUser(userRepository.readAll());
    }

    @Override
    public User readById(UUID uuid) {
        return convertToUser(userRepository.readById(uuid));
    }

    @Override
    public List<User> readManyByLogin(String login) {
        return convertToListUser(userRepository.readManyByLogin(login));
    }
}
