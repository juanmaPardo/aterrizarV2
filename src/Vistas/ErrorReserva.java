
package Vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ErrorReserva extends javax.swing.JFrame {

    
    public ErrorReserva() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
    }
    
    public ErrorReserva(String mensajeError) {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        errorEnReserva.setText(mensajeError);
        botonAceptar.addActionListener(new ActionListener(){

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

        noSePudo = new javax.swing.JLabel();
        errorEnReserva = new javax.swing.JLabel();
        intentaNuevamente = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        noSePudo.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        noSePudo.setText("Ha ocurrido un error en su reserva:");

        errorEnReserva.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        errorEnReserva.setText("<descripción del error>");

        intentaNuevamente.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        intentaNuevamente.setText("Por favor intente nuevamente");

        botonAceptar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
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
                        .addComponent(noSePudo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(errorEnReserva))
                    .addComponent(intentaNuevamente)
                    .addComponent(botonAceptar))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noSePudo)
                    .addComponent(errorEnReserva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intentaNuevamente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(ErrorReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ErrorReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ErrorReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ErrorReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ErrorReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JLabel errorEnReserva;
    private javax.swing.JLabel intentaNuevamente;
    private javax.swing.JLabel noSePudo;
    // End of variables declaration//GEN-END:variables
}
