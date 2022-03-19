package Port;


import com.example.kregielniaspring.exceptions.LoginInUseException;

import java.util.List;
import java.util.UUID;

public interface Port<T> {
    List<T> readAll();

    T readById(UUID uuid);

    T create(T object) throws LoginInUseException;

    T delete(UUID uuid);

    T update(T object) throws LoginInUseException;
}
