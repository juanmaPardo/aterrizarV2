package AterrizarDummy;

import aterrizarv2.usuarios.UsuarioPaga;
import AterrizarDummy.LanchitaDummyBuilder;
import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanic;


public class AterrizarDummy {
    private static UsuarioPaga user = UsuarioDummyBuilder.getUsuarioDummy();
    private static AerolineaLanchita lanchita = LanchitaDummyBuilder.getLanchitaDummyBuilder(user);
    private static AerolineaOceanic oceanic = OceanicDummyBuilder.getOceanicDummyBuilder(user);
    private static AterrizarV2 aterrizarDummy = setearAterrizarDummy();
    
    private static AterrizarV2 setearAterrizarDummy(){
        AterrizarV2 aterrizar = new AterrizarV2();
        aterrizar.agregarAerolinea(lanchita);
        aterrizar.agregarAerolinea(oceanic);
        return aterrizar;
    }
    
    public static AterrizarV2 getPagina(){
        return aterrizarDummy;
    }
    
    public static UsuarioPaga getUsuario(){
        return user;
    }
}
