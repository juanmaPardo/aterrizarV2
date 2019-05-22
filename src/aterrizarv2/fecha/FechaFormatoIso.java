package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;

public class FechaFormatoIso extends Fecha {
    final static String DATE_FORMAT = "yyyy-MM-dd";
    
    public FechaFormatoIso(String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException {
        super(fecha,DATE_FORMAT);
        this.dia = substraerDia(fecha);
        this.mes = substraerMes(fecha);
        this.anio = substraerAnio(fecha);
    }
    protected int substraerDia(String fecha){
        return Integer.parseInt(fecha.substring(8,10));
    }
    protected int substraerMes(String fecha){
        return Integer.parseInt(fecha.substring(5,7));
    }
    protected int substraerAnio(String fecha){
        return Integer.parseInt(fecha.substring(0,4));
    }
}
