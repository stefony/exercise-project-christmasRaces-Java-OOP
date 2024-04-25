package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private static final int MIN_HORSEPOWER = 400;
    private static final int MAX_HORSEPOWER = 600;
    private static final double CUBIC_CENTIMETERS = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, 5000);
        if (!isValidHorsePower(horsePower)) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
    }

    @Override
    protected boolean isValidHorsePower(int horsePower) {
        return horsePower >= 400 && horsePower <=600;
    }
}




