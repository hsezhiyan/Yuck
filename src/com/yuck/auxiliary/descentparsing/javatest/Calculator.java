package com.yuck.auxiliary.descentparsing.javatest;

import com.yuck.auxiliary.descentparsing.GrammarBase;
import com.yuck.auxiliary.descentparsing.annotations.Rule;
import com.yuck.auxiliary.descentparsing.annotations.Start;

import java.util.function.Function;

public class Calculator extends GrammarBase<String> {
  @Rule(rule = "E -> n $E'")
  @Start
  public int E(String n, Function<Integer, Integer> op) {
    return op.apply(Integer.valueOf(n));
  }

  @Rule(rule = "E' -> %eps")
  public Function<Integer, Integer> E_() {
    return n -> n;
  }

  @Rule(rule = "E' -> op $E")
  public Function<Integer, Integer> E_(String op, int right) {
    return getOperation(op, right);
  }

  Function<Integer, Integer> getOperation(String op, int right) {
    switch (op) {
      case "+":
        return n -> n + right;
      case "-":
        return n -> n - right;
      case "*":
        return n -> n * right;
      case "/":
        return n -> n / right;
    }
    throw new IllegalStateException();
  }

  @Override
  public String label(String token) {
    try {
      Integer.valueOf(token);
      return "n";
    } catch (Exception e) {
      switch (token) {
        case "+":
        case "-":
        case "*":
        case "/":
          return "op";
      }
      throw new IllegalStateException();
    }
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    System.err.println(calculator.parseRule("E -> n $E'"));
    calculator.preprocess();
  }
}