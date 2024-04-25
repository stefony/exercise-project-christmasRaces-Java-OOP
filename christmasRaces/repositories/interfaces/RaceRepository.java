package christmasRaces.repositories.interfaces;

import christmasRaces.entities.races.Race;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RaceRepository implements Repository<Race> {
    private Set<Race> models;

    public RaceRepository() {
        this.models = new HashSet<>();
    }

    @Override
    public void add(Race model) {
        models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return models.remove(model);
    }

    @Override
    public Race getByName(String name) {
        return models.stream()
                .filter(race -> race.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableSet(models);
    }
}

