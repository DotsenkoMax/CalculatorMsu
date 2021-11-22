package ru.msu.algo.polandcalc;

import ru.msu.algo.token.Token;

public interface Calculator {
    double calculate(Iterable<Token> stream);
}
