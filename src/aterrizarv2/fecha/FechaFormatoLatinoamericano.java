package aterrizarv2.fecha;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;

public class FechaFormatoLatinoamericano extends Fecha {
    final static String DATE_FORMAT = "dd/MM/yyyy";
    
    public FechaFormatoLatinoamericano(String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException{
        super(fecha,DATE_FORMAT);
        this.dia = substraerDia(fecha);
        this.mes = substraerMes(fecha);
        this.anio = substraerAnio(fecha);
    }
    protected int substraerDia(String fecha){
        return Integer.parseInt(fecha.substring(0,2));
    }
    
    protected int substraerMes(String fecha){
        return Integer.parseInt(fecha.substring(3,5));
    }
    
    protected int substraerAnio(String fecha){
        return Integer.parseInt(fecha.substring(6,10));
    }
  
}
