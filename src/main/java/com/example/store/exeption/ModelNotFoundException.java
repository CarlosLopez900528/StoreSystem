package com.example.store.exeption;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(String messageError){
        super(messageError);
    }
}
