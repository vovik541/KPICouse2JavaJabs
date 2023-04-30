package org.javacore.exception;

public class WrongNumberProvidedException extends Exception {

    private static final String INPUT_NOT_VALID_MESSAGE = "Process choice failure. Please, chose number between %d and %d";

    public WrongNumberProvidedException(int min, int max) {
        super(String.format(INPUT_NOT_VALID_MESSAGE, min, max));
    }
}
