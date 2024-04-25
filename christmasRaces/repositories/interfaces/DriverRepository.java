package christmasRaces.repositories.interfaces;

import christmasRaces.entities.drivers.Driver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DriverRepository implements Repository<Driver> {
    private Set<Driver> models;

    public DriverRepository() {
        this.models = new HashSet<>();
    }

    @Override
    public void add(Driver model) {
        models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return models.remove(model);
    }

    @Override
    public Driver getByName(String name) {
        return models.stream()
                .filter(driver -> driver.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableSet(models);
    }
}
