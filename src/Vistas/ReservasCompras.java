
package Vistas;

import Controladores.ControladorComprasReservas;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import controladorVistas.ComprasController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ReservasCompras extends javax.swing.JFrame {
       
    ControladorComprasReservas controladorCR;
    
        public void setearAsientosEnVista(LinkedList<Asiento> asientosRellenar){
        this.eliminarCeldasTabla();
        asientosRellenar.forEach(asiento -> {
            try {
                String codigoAsiento = asiento.getCodigo().getCodigo();
                String codigoVuelo = asiento.getCodigo().getNumeroVuelo();
                String numeroAsiento = asiento.getCodigo().getNumeroAsiento();
                String fechaSalida = pagina.obtenerFechaSalidaAsiento(codigoAsiento);
                String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
                String precio = Double.toString(asiento.getPrecio().getPrecioAsiento());
                vista.rellenarFilaTabla(fechaSalida,aerolinea,codigoVuelo,numeroAsiento,precio);
            } catch (CodigoAsientoException ex) {
                //Nunca va a entrar a este catch en realidad asi que no pasa nada
                Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public ReservasCompras() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        agregarFuncionalidadBotonCierra();
    }
    
    public ReservasCompras(String nombreUser,String tipoVentana) {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        setearNombreVentana(tipoVentana);
        setearNombreUsuario(nombreUser);
        agregarFuncionalidadBotonCierra();
    }
    
    public void setearNombreVentana(String tipoVentana){
        if(tipoVentana.equals("compras")){
            nombreLabelVentana.setText("Compras de: ");
        }
        else{
            nombreLabelVentana.setText("Reservas de: ");
        }
    }
    
    public void agregarFuncionalidadBotonCierra(){
        botonCerrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               cerrarVentana();
            }
        });
    }
    
    public void cerrarVentana(){
        this.setVisible(false);
        this.dispose();
    }
    
    public void eliminarCeldasTabla(){
        DefaultTableModel tb = (DefaultTableModel) infoTablaUsuario.getModel();
        int a = infoTablaUsuario.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    private void setearNombreUsuario(String nombreUser){
        this.nombreUsuario.setText(nombreUser);
    }
    
    public void rellenarFilaTabla(String fechaSalida, String aerolinea, String codigoVuelo, String nroAsiento, String precio){
        DefaultTableModel modelo = (DefaultTableModel) infoTablaUsuario.getModel();
        Object[] filaAgregar = {fechaSalida,aerolinea,codigoVuelo,nroAsiento,precio};
        modelo.addRow(filaAgregar);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreLabelVentana = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTablaUsuario = new javax.swing.JTable();
        botonCerrar = new javax.swing.JButton();
        nombreUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombreLabelVentana.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        nombreLabelVentana.setText("Reservas de ");

        infoTablaUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int i, int n){
                return false;
            }
        };
        infoTablaUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        infoTablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Salida", "Aerolinea", "Vuelo", "Asiento", "Precio"
            }
        ));
        infoTablaUsuario.setFocusable(false);
        infoTablaUsuario.getTableHeader().setResizingAllowed(false);
        infoTablaUsuario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(infoTablaUsuario);

        botonCerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonCerrar.setText("Cerrar");

        nombreUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        nombreUsuario.setText("XXXXXX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreLabelVentana)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreUsuario))
                    .addComponent(botonCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabelVentana)
                    .addComponent(nombreUsuario))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(botonCerrar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservasCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservasCompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCerrar;
    private javax.swing.JTable infoTablaUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreLabelVentana;
    private javax.swing.JLabel nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
