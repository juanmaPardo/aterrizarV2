package aterrizarv2.fecha.excepcionesFecha;

public class FechaNoValidaException extends InstantiationException{

    public FechaNoValidaException() {
    }

    public FechaNoValidaException(String mensaje) {
        super(mensaje);
    }   
}
