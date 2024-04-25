package christmasRaces.core.interfaces;

import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller{
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        if (driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format("Driver %s is already created.", driverName));
        }

        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);

        return String.format(DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car;

        if ("Muscle".equalsIgnoreCase(type)) {
            car = new MuscleCar(model, horsePower);
        } else if ("Sports".equalsIgnoreCase(type)) {
            car = new SportsCar(model, horsePower);
        } else {
            throw new IllegalArgumentException(String.format("Invalid car type: %s", type));
        }

        carRepository.add(car);

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }


    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = carRepository.getByName(carModel);

        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(CAR_ADDED , driverName, carModel);
    }

    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = driverRepository.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);

        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        int participants= 3;
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (race.getDrivers().size() < participants) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName,participants));
        }

        List<Driver> winners = race.getDrivers().stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(race.getLaps()), d1.getCar().calculateRacePoints(race.getLaps())))
                .limit(participants)
                .collect(Collectors.toList());

        raceRepository.remove(race);

        return String.format("Driver %s wins %s race.%nDriver %s is second in %s race.%nDriver %s is third in %s race.",
                winners.get(0).getName(), raceName,
                winners.get(1).getName(), raceName,
                winners.get(2).getName(), raceName);
    }
    //DRIVER_FIRST_POSITION, DRIVER_SECOND_POSITION,DRIVER_THIRD_POSITION,


    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }
}
