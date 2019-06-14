package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class Compras extends javax.swing.JFrame {

    
    public Compras() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        agregarFuncionalidadBotonCierra();
    }
    
    public Compras(String nombreUser) {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        setearNombreUsuario(nombreUser);
        agregarFuncionalidadBotonCierra();
    }
    
    public void agregarFuncionalidadBotonCierra(){
        cierraVentana.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               cerrarVentana();
            }
        });
    }
    
    public void eliminarCeldasTabla(){
        DefaultTableModel tb = (DefaultTableModel) comprasUsuario.getModel();
        int a = comprasUsuario.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public void cerrarVentana(){
        this.setVisible(false);
        this.dispose();
    }
    
    public void rellenarFilaComprados(String fechaSalida, String aerolinea, String codigoVuelo, String nroAsiento, String precio){
        DefaultTableModel modelo = (DefaultTableModel) comprasUsuario.getModel();
        Object[] filaAgregar = {fechaSalida,aerolinea,codigoVuelo,nroAsiento,precio};
        modelo.addRow(filaAgregar);
    }
    
    private void setearNombreUsuario(String nombreUser){
        this.nombreUsuario.setText(nombreUser);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        compras = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        comprasUsuario = new javax.swing.JTable();
        cierraVentana = new javax.swing.JButton();
        nombreUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        compras.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        compras.setText("Compras de ");

        comprasUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        comprasUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        comprasUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Salida", "Aerolinea", "Vuelo", "Asiento", "Precio"
            }
        ));
        comprasUsuario.setFocusable(false);
        comprasUsuario.getTableHeader().setResizingAllowed(false);
        comprasUsuario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(comprasUsuario);

        cierraVentana.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        cierraVentana.setText("Cerrar");

        nombreUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        nombreUsuario.setText("XXXXXX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cierraVentana)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(compras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreUsuario)))
                        .addGap(0, 220, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compras)
                    .addComponent(nombreUsuario))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cierraVentana)
                .addContainerGap(23, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cierraVentana;
    private javax.swing.JLabel compras;
    private javax.swing.JTable comprasUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
