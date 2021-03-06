package com.yuck.ast;

import com.google.common.collect.ImmutableList;
import com.yuck.grammar.Token;
import com.yuck.ycode.Opcode;
import com.yuck.compilation.YCodeCompilationContext;
import com.yuck.ycode.YCodeFunction;

import java.util.List;

public class Call extends Expression {
  private final Expression left;
  public final ImmutableList<Expression> arguments;

  public Call(Expression left, List<Expression> arguments, Token right) {
    super(left.getStartLine(), left.getStartColumn(), right.endLine, right.endColumn);
    this.left = left;
    this.arguments = ImmutableList.copyOf(arguments);
  }

  @Override
  public YCodeFunction compile(YCodeFunction function, YCodeCompilationContext context) {
    left.compile(function, context);
    arguments.forEach(expr -> expr.compile(function, context));
    return function.emit(Opcode.CALL, arguments.size() + 1);
  }
}
