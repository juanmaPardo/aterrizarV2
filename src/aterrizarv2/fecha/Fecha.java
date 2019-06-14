package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fecha implements DatosFecha{
    protected String dia;
    protected String mes;
    protected String anio;

    public Fecha() {}
    
    public Fecha(String fecha, String dateFormat) throws FormatoFechaIncorrectoException, FechaNoValidaException {
        if(!cumpleFormato(fecha,dateFormat)){
            throw new FormatoFechaIncorrectoException("La fecha propiciada no cumple con "
                    + "el formato " + dateFormat);
        }
        if(!fechaValida(fecha,dateFormat)){
            throw new FechaNoValidaException("La fecha propiciada no es valida");
        }
       
    }
    
    protected boolean cumpleFormato(String fecha,String dateFormat){
        //De los numeros se encarga fecha valida, el formato nada mas mira
        //que sea cooerente en relacion al formato dado
        boolean cumpleFormato = true;
        char separador = getSeparadorFormato(dateFormat);
        if(fecha.length() != dateFormat.length())cumpleFormato = false;
        for(int i = 0 ; i < dateFormat.length() && cumpleFormato; i++){
            if(dateFormat.charAt(i) == separador ){
                cumpleFormato = (fecha.charAt(i) == separador);
            }
            else{
                cumpleFormato = Character.isDigit(fecha.charAt(i));
            }
        }
        return cumpleFormato;
    }
    
    private char getSeparadorFormato(String formato){
        for(int i = 0 ; i < formato.length() ; i++){
            if(formato.charAt(i) == '/' || formato.charAt(i) == '-' ) return formato.charAt(i);
        }
        //Como los formatos son constantes y tienen separadaroes no va a llegar aca, es para
        //no crear una variable y retornar eso, directamente retornamos en el for
        return ' ';
    }
    
    protected boolean fechaValida(String fecha, String dateFormat){
        try {
            System.out.println(fecha + " ..... " + dateFormat);
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    @Override
    public String getDia() {
        return dia;
    }

    @Override
    public String getMes() {
        return mes;
    }

    @Override
    public String getAnio() {
        return anio;
    }
    
    @Override
    public String representacionEnIso(){
        return anio + "-" + mes + "-" + dia;
    }
    
    @Override
    public String representacionEnNorteamericano(){
        return mes + "-" + dia + "-" + anio;
    }
    
    @Override
    public String representacionEnLatinoamericano(){
        return dia + "/" + mes + "/" + anio;
    }

}
