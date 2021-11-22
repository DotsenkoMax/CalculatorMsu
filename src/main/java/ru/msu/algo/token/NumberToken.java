package ru.msu.algo.token;


import ru.msu.algo.model.TokenEnum;

import java.util.Objects;

public class NumberToken implements Token {
    Long value;

    public NumberToken(char val) {
        if (!Character.isDigit(val)) {
            throw new UnsupportedOperationException(String.format("Unsupported token %s", val));
        }
        value = (long) (val - '0');
    }

    @Override
    public TokenEnum getType() {
        return TokenEnum.NUMBER;
    }

    public Long getValue() {
        return value;
    }

    public void addChar(char val) {
        value *= 10;
        value += (long) (val - '0');
    }

    @Override
    public String toString() {
        return String.format("Number {%s}", value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberToken that = (NumberToken) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
