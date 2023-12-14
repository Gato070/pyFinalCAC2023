package com.ar.cac.tp.exceptions;

public class TransferNotFoundException extends RuntimeException{
    public TransferNotFoundException(String message){
        super(message);
    }
}
