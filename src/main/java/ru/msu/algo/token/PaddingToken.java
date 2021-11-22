package ru.msu.algo.token;

import ru.msu.algo.model.TokenEnum;

public class PaddingToken implements Token {
    @Override
    public TokenEnum getType() {
        return TokenEnum.PADDING;
    }
    @Override
    public String toString() {
        return "Padding";
    }
}
