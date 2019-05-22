package aterrizarv2.asientos;

public class UbicacionAsiento {
    private EnumUbicacionAsiento ubicacionAsiento;

    public UbicacionAsiento(EnumUbicacionAsiento ubicacionAsiento) {
        this.ubicacionAsiento = ubicacionAsiento;
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
