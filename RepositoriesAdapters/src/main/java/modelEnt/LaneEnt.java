package modelEnt;

import java.util.Objects;
import java.util.UUID;

public class LaneEnt {

    private UUID uuid;
    private LANE_TYPE_Ent type;

    public LaneEnt(UUID uuid, LANE_TYPE_Ent type) {
        this.uuid = uuid;
        this.type = type;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LANE_TYPE_Ent getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Lane{" +
                "uuid=" + uuid +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaneEnt)) return false;
        LaneEnt lane = (LaneEnt) o;
        return Objects.equals(getUuid(), lane.getUuid()) && getType() == lane.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getType());
    }

    public void setType(LANE_TYPE_Ent type) {
        this.type = type;
    }
}

