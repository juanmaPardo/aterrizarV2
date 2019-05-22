package aterrizarv2.asientos;

public class Asiento {
    ClaseAsiento clase;
    CodigoAsiento codigo;
    EstadoAsiento estado;
    PrecioAsiento precio;
    UbicacionAsiento ubicacion;

    public Asiento(ClaseAsiento clase, CodigoAsiento codigo, EstadoAsiento estado, PrecioAsiento precio, UbicacionAsiento ubicacion) {
        this.clase = clase;
        this.codigo = codigo;
        this.estado = estado;
        this.precio = precio;
        this.ubicacion = ubicacion;
    }
    
    
}
