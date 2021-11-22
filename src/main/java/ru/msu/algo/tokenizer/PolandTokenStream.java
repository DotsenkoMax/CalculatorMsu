package ru.msu.algo.tokenizer;

import ru.msu.algo.model.Bracket;
import ru.msu.algo.token.*;

import java.util.Iterator;
import java.util.LinkedList;

public class PolandTokenStream implements Iterable<Token> {

    private final InfixTokenStream stream;

    public PolandTokenStream(InfixTokenStream stream) {
        this.stream = stream;
    }

    @Override
    public Iterator<Token> iterator() {
        Iterator<Token> infixTokenIterator = stream.iterator();
        LinkedList<Token> tokenStack = new LinkedList<>();
        final OperationToken[] lastOperationToken = {null};
        final BracketToken[] lastBracketToken = {null};

        return new Iterator<Token>() {
            @Override
            public boolean hasNext() {
                return !(!infixTokenIterator.hasNext() && tokenStack.isEmpty());
            }

            @Override
            public Token next() {
                boolean previousAnswer = hasNext();
                if (!previousAnswer) return null;
                Token token = new PaddingToken();
                if (lastBracketToken[0] != null) {
                    Token lastTokenOnStack = tokenStack.getLast();
                    if (lastTokenOnStack instanceof BracketToken && ((BracketToken) lastTokenOnStack).getBracket() == Bracket.OPEN_BRACKET) {
                        lastBracketToken[0] = null;
                        tokenStack.pollLast();
                        return token;
                    } else if (lastTokenOnStack instanceof OperationToken) {
                        return tokenStack.pollLast();
                    } else {
                        throw new UnsupportedOperationException("Open Bracket has been forgotten");
                    }
                }

                if (lastOperationToken[0] != null) {
                    if (tokenStack.isEmpty()) {
                        tokenStack.add(lastOperationToken[0]);
                        lastOperationToken[0] = null;
                        return token;
                    }
                    Token lastTokenOnStack = tokenStack.getLast();
                    if (lastTokenOnStack instanceof OperationToken) {
                        if (((OperationToken) lastTokenOnStack).greater(lastOperationToken[0])) {
                            return tokenStack.pollLast();
                        }
                    }
                    tokenStack.add(lastOperationToken[0]);
                    lastOperationToken[0] = null;
                    return token;
                }

                if (infixTokenIterator.hasNext()) {
                    Token infixToken = infixTokenIterator.next();
                    if (infixToken instanceof NumberToken) {
                        return infixToken;
                    } else if (infixToken instanceof OperationToken) {
                        lastOperationToken[0] = (OperationToken) infixToken;
                        return token;
                    } else if (infixToken instanceof BracketToken) {
                        if (((BracketToken) infixToken).getBracket() == Bracket.CLOSE_BRACKET) {
                            lastBracketToken[0] = (BracketToken) infixToken;
                        } else {
                            tokenStack.add(infixToken);
                        }
                        return token;
                    } else {
                        throw new UnsupportedOperationException("UnsupportedToken");
                    }
                } else {
                    Token lastToken = tokenStack.pollLast();
                    if (lastToken instanceof BracketToken) {
                        return token;
                    } else if (lastToken instanceof OperationToken) {
                        return lastToken;
                    } else {
                        throw new UnsupportedOperationException("UnsupportedToken on Stack");
                    }
                }
            }
        };
    }
}
