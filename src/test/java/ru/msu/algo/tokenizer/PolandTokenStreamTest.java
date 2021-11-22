package ru.msu.algo.tokenizer;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.algo.token.NumberToken;
import ru.msu.algo.token.OperationToken;
import ru.msu.algo.token.Token;

import java.util.List;
import java.util.stream.Stream;

public class PolandTokenStreamTest extends BaseStreamTest {
    @ParameterizedTest()
    @MethodSource("providePolandTokenStreamTests")
    public void polandTokenStreamTest(String initial, List<Token> expected) {
        InfixTokenStream stream = new InfixTokenStream(initial);
        PolandTokenStream tokens = new PolandTokenStream(stream);
        List<Token> actual = toSimpleList(tokens);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }


    protected static Stream<Arguments> providePolandTokenStreamTests() {
        return Stream.of(
                Arguments.of("3*2", List.of(
                        new NumberToken('3'),
                        new NumberToken('2'),
                        new OperationToken('*')
                )),
                Arguments.of("(3*2)", List.of(
                        new NumberToken('3'),
                        new NumberToken('2'),
                        new OperationToken('*')
                )),
                Arguments.of("5 + (3*2)", List.of(
                        new NumberToken('5'),
                        new NumberToken('3'),
                        new NumberToken('2'),
                        new OperationToken('*'),
                        new OperationToken('+')
                )),
                Arguments.of("(5 + (3*2) / 1) / 2 + 9-3* 4", List.of(
                        new NumberToken('5'),
                        new NumberToken('3'),
                        new NumberToken('2'),
                        new OperationToken('*'),
                        new NumberToken('1'),
                        new OperationToken('/'),
                        new OperationToken('+'),
                        new NumberToken('2'),
                        new OperationToken('/'),
                        new NumberToken('9'),
                        new OperationToken('+'),
                        new NumberToken('3'),
                        new NumberToken('4'),
                        new OperationToken('*'),
                        new OperationToken('-')
                )),
                Arguments.of("(1)", List.of(
                        new NumberToken('1')
                )),
                Arguments.of("1", List.of(
                        new NumberToken('1')
                ))
        );
    }
}
