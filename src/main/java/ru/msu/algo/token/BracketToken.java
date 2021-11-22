package ru.msu.algo.token;

import ru.msu.algo.model.Bracket;
import ru.msu.algo.model.TokenEnum;

import java.util.Objects;

public class BracketToken implements Token {
    private final Bracket tokEnum;

    public BracketToken(char val) {
        tokEnum = Bracket.getMap().get(val);
    }

    @Override
    public TokenEnum getType() {
        return TokenEnum.BRACKET;
    }

    public Bracket getBracket() {
        return tokEnum;
    }
    @Override
    public String toString() {
        return String.format("Bracket{%s}", tokEnum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BracketToken that = (BracketToken) o;
        return tokEnum == that.tokEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokEnum);
    }
}