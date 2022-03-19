package repository;



import exceptions.LoginInUseException;
import modelEnt.AdministratorEnt;
import modelEnt.ClientEnt;
import modelEnt.ResourceAdministratorEnt;
import modelEnt.UserEnt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository implements RepositoryInterface<UserEnt>{

    private final List<UserEnt> userList;

    public UserRepository() throws LoginInUseException {
        this.userList = new ArrayList<>();
        this.create(new AdministratorEnt(UUID.fromString("484e945c-9174-417a-b4e4-7736254ade4f"), "miciukaciu", "czesc",true, "Administrator"));
        this.create(new ClientEnt(UUID.fromString("b2998c63-4621-443e-bf59-1e39e1f80170"), "pypensz", "czesc",true, "Client"));
        this.create(new ResourceAdministratorEnt(UUID.fromString("6286cfa3-2993-44d3-aff4-a26ca9b2b75b"), "fici", "czesc",true, "ResourceAdministrator"));
    }

    @Override
    public List<UserEnt> readAll() {
        return userList;
    }

    @Override
    public UserEnt readById(UUID uuid) {
        return userList.stream().filter(user -> uuid.equals(user.getUuid())).findFirst().orElse(null);
    }

    @Override
    public UserEnt create(UserEnt object) throws LoginInUseException {
        if(object.getLogin()=="") throw new LoginInUseException("Login cannot be empty");
        if (loginExists(userList, object.getLogin())) throw new LoginInUseException("This login is already in use.");
        if(object.getUuid()==null || checkIfExists(userList,object.getUuid())){
            if (loginExists(userList, object.getLogin())) return null;
            UUID uuid = UUID.randomUUID();
            while (checkIfExists(userList, uuid)) {
                uuid = UUID.randomUUID();
            }
            object.setUuid(uuid);
        }
        userList.add(object);
        return object;
    }

    @Override
    public UserEnt delete(UUID uuid) {
        return null;
    }

    @Override
    public UserEnt update(UserEnt object) throws LoginInUseException {
        UUID uuid = object.getUuid();
        Optional<UserEnt> optional = userList.stream().filter(user -> uuid.equals(user.getUuid())).findFirst();
        UserEnt user = optional.orElse(null);
        if (user != null) {
            if (loginExists(userList, object.getLogin())) throw new LoginInUseException("This login is already in use.");
            if (object.getLogin() != null) {
                if(object.getLogin()=="") throw new LoginInUseException("Login cannot be empty");
                user.setLogin(object.getLogin());
            }
            if (object.getPassword() != null) user.setPassword(object.getPassword());
        }
        return user;
    }

    public List<UserEnt> readManyByLogin(String login) {
        Stream<UserEnt> stream = userList.stream().filter(user -> user.getLogin().contains(login));
        return stream.collect(Collectors.toList());

    }

    public UserEnt activate(UUID uuid) {
        UserEnt user = this.readById(uuid);
        if (user != null) user.setActive(true);
        return user;
    }

    public UserEnt deactivate(UUID uuid) {
        UserEnt user = this.readById(uuid);
        if (user != null) user.setActive(false);
        return user;
    }

    private static boolean loginExists(List<UserEnt> list, String login) {
        return list.stream().anyMatch(user -> login.equals(user.getLogin()));
    }

    private static boolean canBeEdited(List<UserEnt> list, String login) {
        int count = (int) list.stream().filter(user -> login.equals(user.getLogin())).count();
        if (count > 1) return true;
        return false;
    }

    private static boolean checkIfExists(List<UserEnt> list, UUID uuid) {
        return list.stream().anyMatch(user -> user.getUuid().equals(uuid));
    }

}
