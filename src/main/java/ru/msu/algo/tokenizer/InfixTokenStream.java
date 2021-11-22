package ru.msu.algo.tokenizer;

import ru.msu.algo.model.TokenEnum;
import ru.msu.algo.token.NumberToken;
import ru.msu.algo.token.Token;

import java.util.Iterator;

public class InfixTokenStream implements Iterable<Token> {

    private final String input;

    public InfixTokenStream(String input) {
        this.input = input;
    }

    @Override
    public Iterator<Token> iterator() {

        return new Iterator<Token>() {
            private int curr = 0;

            @Override
            public boolean hasNext() {
                skipWhiteSpaces();
                return curr < input.length();
            }

            @Override
            public Token next() {
                skipWhiteSpaces();
                char currChar = input.charAt(curr);
                Token token = null;
                for (TokenEnum each : TokenEnum.values()) {
                    if (token == null) token = each.getTokenOrNull(currChar);
                }
                ++curr;
                if (token.getType() == TokenEnum.NUMBER) {
                    while (curr < input.length() && Character.isDigit(input.charAt(curr))) {
                        ((NumberToken) token).addChar(input.charAt(curr));
                        ++curr;
                    }
                }
                return token;
            }

            private void skipWhiteSpaces() {
                while (curr < input.length() && Character.isWhitespace(input.charAt(curr))) ++curr;
            }
        };
    }
}
