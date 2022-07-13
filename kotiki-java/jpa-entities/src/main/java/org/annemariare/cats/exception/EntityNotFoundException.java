package org.annemariare.cats.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity was not found :(");
    }
}
