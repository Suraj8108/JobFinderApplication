package com.exceptions;

public class NoSuchInterviewFoundException extends Exception {
  public NoSuchInterviewFoundException(int id) {
    super("No interview with id " + id + " found:( ");
  }
}
