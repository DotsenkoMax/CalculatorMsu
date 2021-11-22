package ru.msu.algo.model;

import ru.msu.algo.token.BracketToken;
import ru.msu.algo.token.NumberToken;
import ru.msu.algo.token.OperationToken;
import ru.msu.algo.token.Token;

public enum TokenEnum {
    BRACKET,
    OPERATION,
    NUMBER,
    PADDING;

    public Token getTokenOrNull(char tok) {
        if (Bracket.getOperationCharacteristic().contains(tok)) {
            return new BracketToken(tok);
        } else if (Operation.getOperationCharacteristic().contains(tok)) {
            return new OperationToken(tok);
        } else {
            return new NumberToken(tok);
        }
    }
}


