package com.cgrdev.whitecollar.domain.controller;

public class DuplicateSoreException extends RuntimeException {
    public DuplicateSoreException(String name) {
        super("Error: Painting with name \"" + name + "\" already exists.\nPainting not added.");
    }
}
