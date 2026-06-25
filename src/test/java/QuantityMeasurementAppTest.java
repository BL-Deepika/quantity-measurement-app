import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(kilogram.equals(gram));
    }

    @Test
    public void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.GRAM
                );

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new Quantity<>(
                                12.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.FEET
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new Quantity<>(
                                1000.0,
                                WeightUnit.GRAM
                        ),
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testCrossCategoryPrevention() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(feet.equals(kilogram));
    }

    @Test
    public void testConstructorValidation_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                ()->new Quantity<>(
                        1.0,
                        null
                ));
    }

    @Test
    public void testConstructorValidation_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(
                        Double.NaN,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    public void testHashCodeConsistency() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertEquals(
                feet.hashCode(),
                inches.hashCode()
        );
    }

    @Test
    public void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(
                        1.0,
                        null
                )
        );
    }

    @Test
    public void testEquality_LargeWeightValue() {

        assertTrue(
                new QuantityWeight(
                        1000000.0,
                        WeightUnit.GRAM
                ).equals(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }
}