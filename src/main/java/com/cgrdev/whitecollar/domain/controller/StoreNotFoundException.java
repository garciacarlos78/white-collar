package com.cgrdev.whitecollar.domain.controller;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(Long id) {
        super("Could not find store " + id);
    }
}
