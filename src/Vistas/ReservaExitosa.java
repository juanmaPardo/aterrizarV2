
package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ReservaExitosa extends javax.swing.JFrame {

    
    public ReservaExitosa() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        
    }
    
    public ReservaExitosa(String codigoAsiento) {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        nombreAsiento.setText(codigoAsiento);
        botonSeguirBuscando.addActionListener(new ActionListener(){

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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        asiento = new javax.swing.JLabel();
        nombreAsiento = new javax.swing.JLabel();
        reservaExitosa = new javax.swing.JLabel();
        botonSeguirBuscando = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        asiento.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        asiento.setText("El asiento");

        nombreAsiento.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        nombreAsiento.setText("XXXXXX");

        reservaExitosa.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        reservaExitosa.setText("ha sido reservado exitosamente");

        botonSeguirBuscando.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonSeguirBuscando.setText("Seguir Buscando");
        botonSeguirBuscando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeguirBuscandoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(asiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreAsiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reservaExitosa))
                    .addComponent(botonSeguirBuscando))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asiento)
                    .addComponent(nombreAsiento)
                    .addComponent(reservaExitosa))
                .addGap(18, 18, 18)
                .addComponent(botonSeguirBuscando)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSeguirBuscandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeguirBuscandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSeguirBuscandoActionPerformed

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
            java.util.logging.Logger.getLogger(ReservaExitosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservaExitosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservaExitosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaExitosa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asiento;
    private javax.swing.JButton botonSeguirBuscando;
    private javax.swing.JLabel nombreAsiento;
    private javax.swing.JLabel reservaExitosa;
    // End of variables declaration//GEN-END:variables
}
