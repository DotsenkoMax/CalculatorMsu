package ru.msu.algo.tokenizer;

import ru.msu.algo.token.PaddingToken;
import ru.msu.algo.token.Token;

import java.util.LinkedList;
import java.util.List;

public class BaseStreamTest {

    protected List<Token> toSimpleList(Iterable<Token> stream) {
        LinkedList<Token> list = new LinkedList<>();
        for (Token each : stream) {
            if (each instanceof PaddingToken) continue;
            list.add(each);
        }
        return list;
    }
}
