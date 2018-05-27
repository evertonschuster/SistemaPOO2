package br.edu.udc.sistemas.poo2.infra;

public	class ExceptionValidacao extends Exception  /* RuntimeException */{

    private static final long serialVersionUID = 1149241039409861914L;

    public ExceptionValidacao(String msg){
        super(msg);
    }

    public ExceptionValidacao(String msg, Throwable cause){
        super(msg, cause);
    }
}