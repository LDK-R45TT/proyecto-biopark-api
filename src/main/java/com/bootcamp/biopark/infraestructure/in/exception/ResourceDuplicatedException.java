package com.bootcamp.biopark.infraestructure.in.exception;

public class ResourceDuplicatedException extends RuntimeException{
    public ResourceDuplicatedException(String message){
        super(message);
    }
}