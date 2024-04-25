package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;
import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car{

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        setCubicCentimeters(cubicCentimeters);
    }



    private void setModel(String model) {
        int symbol = 4;
        if (model == null || model.trim().isEmpty() || model.length() < symbol) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL,model,symbol));
        }
        this.model = model;
    }

    protected void setHorsePower(int horsePower, int minHorsePower, int maxHorsePower) {
        if (horsePower < minHorsePower || horsePower > maxHorsePower) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
        this.horsePower = horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setHorsePower(int horsePower) {
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return getCubicCentimeters() / getHorsePower() * laps;
    }

    protected abstract boolean isValidHorsePower(int horsePower);
}
