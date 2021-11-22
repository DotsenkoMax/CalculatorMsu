package ru.msu.algo.integration;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.algo.polandcalc.PolandCalculator;
import ru.msu.algo.tokenizer.InfixTokenStream;
import ru.msu.algo.tokenizer.PolandTokenStream;

import java.util.stream.Stream;

public class PolandCalculationTest {
    @ParameterizedTest()
    @MethodSource("provideDoubleTest")
    public void integration(String initial, double expected) {
        PolandCalculator calculator = new PolandCalculator();
        InfixTokenStream infixStream = new InfixTokenStream(initial);
        PolandTokenStream polandTokenStream = new PolandTokenStream(infixStream);
        double actual = calculator.calculate(polandTokenStream);
        Assert.assertEquals(expected, actual, 1e-10);
    }

    protected static Stream<Arguments> provideDoubleTest() {
        return Stream.of(
                Arguments.of("5+3", 8.),
                Arguments.of("5+3*2", 11.),
                Arguments.of("(5-3)/10", 0.2),
                Arguments.of("(6 - 6 ) * 10", 0.),
                Arguments.of("7*(7-3) + 3*4 - 1", 39)
        );
    }
}
