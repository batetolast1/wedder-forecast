package io.github.batetolast1.wedderforecast.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    private MathUtils() {
    }

    public static double convertToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double convertToMm(double inch) {
        return inch * 25.4;
    }

    public static double convertToKmh(double mph) {
        return mph * 1.609344;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
