package com.bootcamp.biopark.infraestructure.in.exception;

public class ResourceDeletionNotAllowedException extends RuntimeException{
    public ResourceDeletionNotAllowedException(String message){
        super(message);
    }
}
