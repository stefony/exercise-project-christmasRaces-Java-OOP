package christmasRaces.repositories.interfaces;

import christmasRaces.entities.cars.Car;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CarRepository implements Repository<Car>{

    private Set<Car> models;

    public CarRepository() {
        this.models = new HashSet<>();
    }

    @Override
    public void add(Car model) {
        models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return models.remove(model);
    }

    @Override
    public Car getByName(String name) {
        return models.stream()
                .filter(car -> car.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableSet(models);
    }
}


