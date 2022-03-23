package Port.In;


import model.Lane;

import java.util.UUID;

public interface LanePortIn {

    Lane create(Lane lane);

    Lane delete(UUID uuid);

    Lane update(Lane lane);
}
