package Port;


import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Lane;

import java.util.List;
import java.util.UUID;

public interface LanePort {

    List<Lane> readAll();

    Lane readById(UUID uuid);

    Lane create(Lane object);

    Lane delete(UUID uuid);

    Lane update(Lane object);
}
