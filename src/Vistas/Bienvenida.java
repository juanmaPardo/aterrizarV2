package Vistas;

import AterrizarDummy.AterrizarDummy;
import Controladores.ControladorBienvenida;
import aterrizarv2.AterrizarV2;
import aterrizarv2.usuarios.Usuario;
import java.awt.event.ActionListener;


public class Bienvenida extends javax.swing.JFrame {
    
    private ControladorBienvenida controlador;
  
    
    public Bienvenida() {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        controlador = crearControladorBienvenida();
        setearNombreUsuario();
        setearListeners();
        this.setVisible(true);
        this.setLocation(500, 200);

    }
    
    private void setearNombreUsuario(){
        this.nombreUsuario.setText(controlador.getModelo().obtenerNombreUsuarioLogeado());
    }
    
    private ControladorBienvenida crearControladorBienvenida(){
        return new ControladorBienvenida(AterrizarDummy.getUsuario());
    }
    
    private void setearListeners(){
        setearListenerCompras();
        setearListenerReservas();
        setearListenerBuscador();
    }
    
    public void setearListenerCompras(){
        this.botonCompra.addActionListener(e -> {
            ReservasCompras contCompras = new ReservasCompras("compras");
            contCompras.setVisible(true);
        });
    }
    
    public void setearListenerReservas(){
        this.botonReservas.addActionListener(e -> {
            ReservasCompras contCompras = new ReservasCompras("reservas");
            contCompras.setVisible(true);
        });
    }
    
    public void setearListenerBuscador(){
        this.botonAsientos.addActionListener(e -> {
            BusquedaAsientos busqueda = new BusquedaAsientos();
            busqueda.setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        botonCompra = new javax.swing.JButton();
        botonReservas = new javax.swing.JButton();
        botonAsientos = new javax.swing.JButton();
        hola = new javax.swing.JLabel();
        labelDeseaHacer = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonCompra.setText("Ver Compras");

        botonReservas.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonReservas.setText("Ver Reservas");

        botonAsientos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonAsientos.setText("Buscar Asientos");

        hola.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        hola.setText("Hola");

        labelDeseaHacer.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        labelDeseaHacer.setText("¿Qué desea hacer?");

        nombreUsuario.setText("Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botonReservas, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonAsientos)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDeseaHacer)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hola, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreUsuario)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hola)
                    .addComponent(nombreUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDeseaHacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(botonAsientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonCompra))
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bienvenida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAsientos;
    private javax.swing.JButton botonCompra;
    private javax.swing.JButton botonReservas;
    private javax.swing.JLabel hola;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDeseaHacer;
    private javax.swing.JLabel nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
