
package aterrizarv2.vuelos;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.FechaFormatoLatinoamericano;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;

public class AsientoDTO extends AsientoVueloFullData{
    
    private String codigoVuelo;
    private Integer numeroAsiento;
    private FechaFormatoLatinoamericano fechaSalida;
    private Hora horaSalida;
    private PrecioAsiento precio;
    private ClaseAsiento clase;
    private UbicacionAsiento ubicacion;
    
    
    public AsientoDTO(Asiento asiento, String origen, String destino, FechaFlexible fechaSal, Hora horaSal) throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException {
        super(asiento, origen, destino, fechaSal, horaSal);
        codigoVuelo = asiento.getCodigo().getCodigo();
        numeroAsiento = Integer.parseInt(asiento.getCodigo().getNumeroAsiento());
        fechaSalida = new FechaFormatoLatinoamericano(fechaSal.representacionEnLatinoamericano());
        horaSalida = new Hora(horaSal.getHoraFormatoString());
        precio = asiento.getPrecio();
        clase = asiento.getClase();
        ubicacion = asiento.getUbicacion();
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public Integer getNumeroAsiento() {
        return numeroAsiento;
    }

    public FechaFormatoLatinoamericano getFechaSalidaDTO() {
        return fechaSalida;
    }

    @Override
    public Hora getHoraSalida() {
        return horaSalida;
    }

    public PrecioAsiento getPrecio() {
        return precio;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public UbicacionAsiento getUbicacion() {
        return ubicacion;
    }

}
