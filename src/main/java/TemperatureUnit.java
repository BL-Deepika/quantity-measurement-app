import java.util.function.Function;

public enum TemperatureUnit implements Measurable {

    CELSIUS(
            c -> c, // to base (Celsius)
            c -> c  // from base
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,        // to Celsius
            c -> (c * 9 / 5) + 32         // from Celsius
    );

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    TemperatureUnit(
            Function<Double, Double> toBase,
            Function<Double, Double> fromBase
    ) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // Not meaningful for temperature
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return name();
    }

    public boolean supportsArithmetic() {
        return false;
    }

    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation +
                        " operation (non-linear scale)"
        );
    }
}