package ru.msu.algo.polandcalc;

import ru.msu.algo.token.NumberToken;
import ru.msu.algo.token.OperationToken;
import ru.msu.algo.token.PaddingToken;
import ru.msu.algo.token.Token;

import java.util.LinkedList;

public class PolandCalculator implements Calculator {

    @Override
    public double calculate(Iterable<Token> stream) {
        LinkedList<Double> stack = new LinkedList<>();
        for (Token token : stream) {
            if (token instanceof PaddingToken) continue;
            if (token instanceof NumberToken) {
                stack.add(((NumberToken) token).getValue().doubleValue());
            } else if (token instanceof OperationToken) {
                if(stack.size() < 2){
                    throw new IllegalArgumentException("Bad Values at Stack");
                }
                double right = stack.pollLast(), left = stack.pollLast();
                stack.add(((OperationToken)token).calculate(left, right));
            }
            else{
                throw new UnsupportedOperationException("UnSupported Stack Operation");
            }
        }
        if (stack.size() != 1){
            throw new IllegalArgumentException("Bad Values at Stack");
        }
        return stack.pollLast();
    }
}
