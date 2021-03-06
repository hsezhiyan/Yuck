package com.yuck.ast;

import com.yuck.grammar.Token;
import com.yuck.ycode.Opcode;
import com.yuck.compilation.YCodeCompilationContext;
import com.yuck.ycode.YCodeFunction;

public class StringLiteral extends Expression {
  public final String text;
  public StringLiteral(Token token) {
    super(token.startLine, token.startColumn, token.endLine, token.endColumn);
    text = token.text;
  }

  @Override
  public YCodeFunction compile(YCodeFunction function, YCodeCompilationContext context) {
    return function.emit(Opcode.LOAD_CONST, text);
  }
}
