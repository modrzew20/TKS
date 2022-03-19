package Port;


import com.example.kregielniaspring.exceptions.LoginInUseException;
import com.example.kregielniaspring.model.Lane;

import java.util.List;
import java.util.UUID;

public interface LanePort extends Port<Lane> {

    @Override
    List<Lane> readAll();

    @Override
    Lane readById(UUID uuid);

    @Override
    Lane create(Lane object) throws LoginInUseException;

    @Override
    Lane delete(UUID uuid);

    @Override
    Lane update(Lane object) throws LoginInUseException;
}
