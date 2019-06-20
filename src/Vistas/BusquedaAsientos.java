package Vistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class BusquedaAsientos extends javax.swing.JFrame {
    
    public BusquedaAsientos() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        agregarFuncionalidadBotones();
    }
    
    public void agregarFuncionalidadBotones(){
        agregarFuncionalidadBotonCierra();
    }
    
    public void agregarFuncionalidadBotonCierra(){
        botonCerrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               cerrarVentana();
            }
        });
    }
    
    public void agregarFuncionalidadBotonReserva(ActionListener eventoReserva){
        botonReservar.addActionListener(eventoReserva);
    }
    
    public void agregarFuncionalidadBotonBuscar(ActionListener eventoBusqueda){
        botonBuscar.addActionListener(eventoBusqueda);
    }
    
    public void agregarFuncionalidadBotonComprar(ActionListener eventoCompra){
        botonComprar.addActionListener(eventoCompra);
    }
    
    public void cambiarTextoTextField(String texto){
        mostrarErrores.setText(texto);
 
    }
    
    public void eliminarFilaSeleccionada(){
        DefaultTableModel tb = (DefaultTableModel) resultadoBusqueda.getModel();
        tb.removeRow(resultadoBusqueda.getSelectedRow());
    }
    
    public void eliminarCeldasTabla(){
        DefaultTableModel tb = (DefaultTableModel) resultadoBusqueda.getModel();
        int a = resultadoBusqueda.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public void rellenarTablaConDisponibles(String aerolinea, String vuelo, String nroAsiento, String precio, String ubicacion, String clase){
        DefaultTableModel modelo = (DefaultTableModel) resultadoBusqueda.getModel();
        Object[] filaAgregar = {aerolinea,vuelo,nroAsiento,precio,ubicacion,clase};
        modelo.addRow(filaAgregar);
    }
    
    public boolean seSeleccionoFila(){
        return (resultadoBusqueda.getSelectedRow() != -1);
    }
    
    
    public String obtenerCodigoVueloFilaSeleccionada(){
        return (String)resultadoBusqueda.getValueAt(resultadoBusqueda.getSelectedRow(),1);
    }
    
    public String obtenerNumeroAsientoFilaSeleccionada(){
        return (String)resultadoBusqueda.getValueAt(resultadoBusqueda.getSelectedRow(),2);
    }
    
    public String obtenerTextoOrigen(){
        return this.textoOrigen.getText();
    }
    
    public String obtenerTextoDestino(){
        return this.textoDestino.getText();
    }
    
    public String obtenerTextoFecha(){
        return this.textoFecha.getText();
    }
    
    public void cerrarVentana(){
        this.setVisible(false);
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mostrarErrores = new javax.swing.JTextField();
        origen = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoDestino = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoOrigen = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoFecha = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        resultadoBusqueda = new javax.swing.JTable();
        botonComprar = new javax.swing.JButton();
        botonReservar = new javax.swing.JButton();
        botonCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mostrarErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarErroresActionPerformed(evt);
            }
        });

        origen.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        origen.setText("Origen:");

        fecha.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        fecha.setText("Fecha:");

        destino.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        destino.setText("Destino:");

        botonBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonBuscar.setText("Buscar");

        textoDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoDestinoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoDestinoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(textoDestino);

        textoOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoOrigenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoOrigenKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(textoOrigen);

        textoFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoFechaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFechaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(textoFecha);

        resultadoBusqueda = new javax.swing.JTable(){
            public boolean isCellEditable(int i, int n){
                return false;
            }
        };
        resultadoBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aerolinea", "Vuelo", "Asiento", "Precio", "Ubicacion", "Clase"
            }
        ));
        resultadoBusqueda.setFocusable(false);
        resultadoBusqueda.getTableHeader().setResizingAllowed(false);
        resultadoBusqueda.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(resultadoBusqueda);

        botonComprar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonComprar.setText("Comprar");

        botonReservar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonReservar.setText("Reservar");
        botonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReservarActionPerformed(evt);
            }
        });

        botonCerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonCerrar.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fecha)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(origen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(43, 43, 43)
                                .addComponent(destino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonComprar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonReservar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCerrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(mostrarErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(origen)
                        .addComponent(destino))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonComprar)
                    .addComponent(botonReservar)
                    .addComponent(botonCerrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarErroresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostrarErroresActionPerformed

    private void botonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReservarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonReservarActionPerformed

    private void textoOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoOrigenKeyTyped
        int numeroCaracteres = 4;
        if(textoOrigen.getText().length() >= numeroCaracteres){
            evt.consume();
        }
    }//GEN-LAST:event_textoOrigenKeyTyped

    private void textoDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDestinoKeyTyped
        int numeroCaracteres = 4;
        if(textoDestino.getText().length() >= numeroCaracteres){
            evt.consume();
        }
    }//GEN-LAST:event_textoDestinoKeyTyped

    private void textoFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFechaKeyTyped
        int numeroCaracteres = 10;
        if(textoFecha.getText().length() >= numeroCaracteres){
            evt.consume();
        }
    }//GEN-LAST:event_textoFechaKeyTyped

    private void textoOrigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoOrigenKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SHIFT){
            textoDestino.requestFocus();
        }
    }//GEN-LAST:event_textoOrigenKeyPressed

    private void textoDestinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDestinoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SHIFT){
            textoFecha.requestFocus();
        }
    }//GEN-LAST:event_textoDestinoKeyPressed

    private void textoFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFechaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SHIFT){
            textoOrigen.requestFocus();
        }
    }//GEN-LAST:event_textoFechaKeyPressed
        
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
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BusquedaAsientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonComprar;
    private javax.swing.JButton botonReservar;
    private javax.swing.JLabel destino;
    private javax.swing.JLabel fecha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mostrarErrores;
    private javax.swing.JLabel origen;
    private javax.swing.JTable resultadoBusqueda;
    private javax.swing.JTextPane textoDestino;
    private javax.swing.JTextPane textoFecha;
    private javax.swing.JTextPane textoOrigen;
    // End of variables declaration//GEN-END:variables
}
