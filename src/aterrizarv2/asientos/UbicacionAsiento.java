package aterrizarv2.asientos;

import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;

public class UbicacionAsiento {
    private EnumUbicacionAsiento ubicacionAsiento;

    public UbicacionAsiento(EnumUbicacionAsiento ubicacionAsiento) {
        this.ubicacionAsiento = ubicacionAsiento;
    }
    
    public UbicacionAsiento(String ubic) throws UbicacionAsientoInvalidaException{
        if(ubic == "P"){
            ubicacionAsiento = EnumUbicacionAsiento.PASILLO;
        }
        else if(ubic == "C"){
            ubicacionAsiento = EnumUbicacionAsiento.CENTRO;
        }
        else if(ubic == "V"){
            ubicacionAsiento = EnumUbicacionAsiento.VENTANA;
        }
        else{
            throw new UbicacionAsientoInvalidaException("El acronimo especificado no hace referencia a ninguna ubicacion de asiento valida");
        }
    }

    public EnumUbicacionAsiento getUbicacionAsiento() {
        return ubicacionAsiento;
    }
    
    /*
    @Override
    public boolean asientoVueloCumpleParametro(AsientoGeneralVuelo asiento) {
        return asiento.getDatosAsiento().getUbicacionAsiento().getUbicacionAsiento() == ubicacionAsiento;
    }*/
}
