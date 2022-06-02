package com.tuyo.tuyofood.domain.exception;

/* 1. RuntimeException: mostrar que herda de UncheckedException
*  2. super: é o RuntimeException. Ou seja, ele chama o construtor RuntimeException
* que recebe uma mensagem. */

public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

}
6