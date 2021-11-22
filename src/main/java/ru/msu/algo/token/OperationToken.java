package ru.msu.algo.token;

import ru.msu.algo.model.Operation;
import ru.msu.algo.model.TokenEnum;

import java.util.Objects;

public class OperationToken implements Token {
    private final Operation tokEnum;

    public OperationToken(char val) {
        tokEnum = Operation.getMap().get(val);
    }

    @Override
    public TokenEnum getType() {
        return TokenEnum.OPERATION;
    }

    public boolean greater(OperationToken rhs) {
        return tokEnum.getPriority() >= rhs.tokEnum.getPriority();
    }

    @Override
    public String toString() {
        return String.format("Operation {%s}", tokEnum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationToken that = (OperationToken) o;
        return tokEnum == that.tokEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokEnum);
    }

    public double calculate(double left, double right) {
        switch (tokEnum) {
            case SUM:
                return left + right;
            case DIVISION:
                return left / right;
            case SUBTRACTION:
                return left - right;
            case MULTIPLICATION:
                return left * right;
            default:
                return 0;
        }
    }
}