package ru.msu.algo.tokenizer;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.algo.token.BracketToken;
import ru.msu.algo.token.NumberToken;
import ru.msu.algo.token.OperationToken;
import ru.msu.algo.token.Token;

import java.util.List;
import java.util.stream.Stream;

public class InfixTokenStreamTest extends BaseStreamTest {
    @ParameterizedTest()
    @MethodSource("provideInfixTokenStreamTests")
    public void infixStreamTest(String initial, List<Token> expected) {
        InfixTokenStream stream = new InfixTokenStream(initial);
        List<Token> actual = toSimpleList(stream);
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    protected static Stream<Arguments> provideInfixTokenStreamTests() {
        return Stream.of(
                Arguments.of("3*2", List.of(
                        new NumberToken('3'),
                        new OperationToken('*'),
                        new NumberToken('2')
                )),
                Arguments.of("(3*2)", List.of(
                        new BracketToken('('),
                        new NumberToken('3'),
                        new OperationToken('*'),
                        new NumberToken('2'),
                        new BracketToken(')')
                )),
                Arguments.of("5 + (3*2)", List.of(
                        new NumberToken('5'),
                        new OperationToken('+'),
                        new BracketToken('('),
                        new NumberToken('3'),
                        new OperationToken('*'),
                        new NumberToken('2'),
                        new BracketToken(')')
                )),
                Arguments.of("(1)", List.of(
                        new BracketToken('('),
                        new NumberToken('1'),
                        new BracketToken(')')
                )),
                Arguments.of("1", List.of(
                        new NumberToken('1')
                ))
        );
    }
}
