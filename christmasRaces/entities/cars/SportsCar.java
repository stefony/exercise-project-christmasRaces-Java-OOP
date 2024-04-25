package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{

    private static final int MIN_HORSEPOWER = 250;
    private static final int MAX_HORSEPOWER = 450;
    private static final double CUBIC_CENTIMETERS = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
        if (!isValidHorsePower(horsePower)) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
    }

    @Override
    protected boolean isValidHorsePower(int horsePower) {
        return horsePower >= 250 && horsePower <= 450;
    }
    }

