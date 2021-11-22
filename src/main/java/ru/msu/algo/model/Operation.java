package ru.msu.algo.model;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Operation {
    MULTIPLICATION('*', 10),
    DIVISION('/', 10),
    SUBTRACTION('-', 5),
    SUM('+', 5);
    private final char ops;
    private final int priority;
    private static final Map<Character, Operation> mapper = getMapper();

    Operation(char ops, int priority) {
        this.ops = ops;
        this.priority = priority;
    }

    public char getOps() {
        return ops;
    }

    public int getPriority() {
        return priority;
    }

    public static Map<Character, Operation> getMap() {
        return mapper;
    }

    public static List<Character> getOperationCharacteristic() {
        return Arrays.stream(Operation.values()).map(value -> value.ops).collect(Collectors.toList());
    }

    private static Map<Character, Operation> getMapper() {
        Map<Character, Operation> mapper = new HashMap<>();
        for (Operation op : Operation.values()) {
            mapper.put(op.ops, op);
        }
        return mapper;
    }

}