package org.annemariare.kotiki.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
        super("Entity was not found :(");
    }
}
