package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race{

    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.drivers = new ArrayList<>();
    }
        Race race;
    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException("Name " + name + " cannot be less than 5 symbols.");
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException("Laps cannot be less than 1.");
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }

        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE,driver));
        }

        if (drivers.contains(driver)) {

            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED,driver,race));
        }

        drivers.add(driver);
    }

    @Override
    public Set<Driver> getDrivers() {
        return new HashSet<>(this.drivers);
    }
}
