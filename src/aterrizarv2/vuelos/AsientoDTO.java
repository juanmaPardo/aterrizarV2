
package aterrizarv2.vuelos;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.FechaFormatoLatinoamericano;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;

public class AsientoDTO extends Asiento{
    
    private FechaFormatoLatinoamericano fechaSalida;
    private Hora horaSalida;

    public AsientoDTO(FechaFormatoLatinoamericano fechaSalida, Hora horaSalida, ClaseAsiento clase, CodigoAsiento codigo, EstadoAsiento estado, PrecioAsiento precio, UbicacionAsiento ubicacion) {
        super(clase, codigo, estado, precio, ubicacion);
        
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
    }

    public FechaFormatoLatinoamericano getFechaSalidaDTO() {
        return fechaSalida;
    }
    
    public Hora getHoraSalida() {
        return horaSalida;
    }
    
}
