package com.codecool.krk.exception;

public class NoSuchElementInDatabaseException extends RuntimeException {

        public NoSuchElementInDatabaseException() {
            super("No such element in database!");
        }

}
