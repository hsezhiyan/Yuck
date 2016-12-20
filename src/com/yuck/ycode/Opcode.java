package com.yuck.ycode;

public enum Opcode {
  LOAD_CONST, LOAD_LOCAL, STORE_LOCAL, LOAD_UP, STORE_UP, POP, ROT2, CLOSURE, CALL,
  TABLE_LOAD, TABLE_STORE, NEW, RETURN, GET_FIELD, PUT_FIELD, LT, LE, EQ, JUMPZ, TO_RANGE,
  ADD, MUL, SUB, DIV, MOD, NEG, NOT, POW, LIST, TABLE, GOTO, NOP, AND, OR
}
