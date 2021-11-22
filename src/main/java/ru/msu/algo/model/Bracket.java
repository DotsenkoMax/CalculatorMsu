package ru.msu.algo.model;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Bracket {
    CLOSE_BRACKET(')'),
    OPEN_BRACKET('(');
    private final char ops;
    private static final Map<Character, Bracket> mapper = getMapper();

    Bracket(char ops) {
        this.ops = ops;
    }

    public char getOps() {
        return ops;
    }

    public static Map<Character, Bracket> getMap() {
        return mapper;
    }

    public static List<Character> getOperationCharacteristic() {
        return Arrays.stream(Bracket.values()).map(value -> value.ops).collect(Collectors.toList());
    }

    private static Map<Character, Bracket> getMapper() {
        Map<Character, Bracket> mapper = new HashMap<>();
        for (Bracket op : Bracket.values()) {
            mapper.put(op.ops, op);
        }
        return mapper;
    }
}