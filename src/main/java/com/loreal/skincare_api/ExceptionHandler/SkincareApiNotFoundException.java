package com.loreal.skincare_api.ExceptionHandler;

public class SkincareApiNotFoundException extends RuntimeException {
    public SkincareApiNotFoundException(Long id) { super("product not found");}
}
