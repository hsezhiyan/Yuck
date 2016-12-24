package com.yuck.ast;

import com.yuck.ycode.Opcode;
import com.yuck.compilation.YCodeCompilationContext;
import com.yuck.ycode.YCodeFunction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BinaryOperator extends Expression {
  public final String operator;
  public final Expression left;
  public final Expression right;

  public BinaryOperator(String operator, Expression left, Expression right) {
    super(left.getStartLine(), left.getStartColumn(), right.getEndLine(), right.getEndColumn());
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  @Override
  public YCodeFunction compile(YCodeFunction function, YCodeCompilationContext context) {
    Opcode opcode;
    boolean not = false;
    switch (operator) {
      case "+": opcode = Opcode.ADD; break;
      case "*": opcode = Opcode.MUL; break;
      case "||":
      case "or": opcode = Opcode.OR; break;
      case "&&":
      case "and": opcode = Opcode.AND; break;
      case ">=": not = true;
      case "<": opcode = Opcode.LT; break;
      case ">": not = true;
      case "<=": opcode = Opcode.LE; break;
      case "!=": not = true;
      case "==": opcode = Opcode.EQ; break;
      case "to": opcode = Opcode.TO_RANGE; break;
      case "-": opcode = Opcode.SUB; break;
      case "/": opcode = Opcode.DIV; break;
      case "mod": opcode = Opcode.MOD; break;
      case "**": opcode = Opcode.POW; break;
      default:
        throw new NotImplementedException();
    }
    left.compile(function, context);
    right.compile(function, context);
    function.emit(opcode);
    if (not) function.emit(Opcode.NOT);
    return function;
  }
}
