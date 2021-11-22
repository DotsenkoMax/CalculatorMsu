package ru.msu.algo;

import ru.msu.algo.polandcalc.PolandCalculator;
import ru.msu.algo.tokenizer.InfixTokenStream;
import ru.msu.algo.tokenizer.PolandTokenStream;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PolandCalculator calculator = new PolandCalculator();
        System.out.println("Application Started!!!");
        System.out.println("Please Enter your lines:");
        while (true) {
            String line = scanner.next();
            if ("q".equals(line)) {
                return;
            }
            InfixTokenStream infixTokenStream = new InfixTokenStream(line);
            PolandTokenStream polandTokenStream = new PolandTokenStream(infixTokenStream);
            System.out.println("Result: " + calculator.calculate(polandTokenStream));
        }
    }
}
