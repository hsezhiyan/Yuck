package com.yuck.ast;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.yuck.compilation.Compilable;
import com.yuck.compilation.YCodeCompilationContext;
import com.yuck.ycode.YCodeFunction;

public abstract class Base implements Compilable<YCodeFunction> {
  @Expose
  public final String type = getClass().getSimpleName();
  private transient int mStartLine, mStartColumn, mEndLine, mEndColumn;

  protected Base(int startLine, int startColumn, int endLine, int endColumn) {
    this.mStartLine = startLine;
    this.mStartColumn = startColumn;
    this.mEndLine = endLine;
    this.mEndColumn = endColumn;
  }

  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

  public int getStartLine() {
    return mStartLine;
  }

  public void setStartLine(int mStartLine) {
    this.mStartLine = mStartLine;
  }

  public int getStartColumn() {
    return mStartColumn;
  }

  public void setStartColumn(int mStartColumn) {
    this.mStartColumn = mStartColumn;
  }

  public int getEndLine() {
    return mEndLine;
  }

  public void setEndLine(int mEndLine) {
    this.mEndLine = mEndLine;
  }

  public int getEndColumn() {
    return mEndColumn;
  }

  public void setEndColumn(int mEndColumn) {
    this.mEndColumn = mEndColumn;
  }

  public YCodeFunction compile(YCodeFunction function, YCodeCompilationContext context) {
    throw new RuntimeException(String.format("%s.compile() is not implemented.", getClass().getSimpleName()));
  }
}
