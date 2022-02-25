package View;

import datechooser.beans.DateChooserCombo;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author lucia
 */
public class ViewAlumno extends javax.swing.JFrame {

    public ViewAlumno() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPAlumno = new javax.swing.JPanel();
        jLAlumnoTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlumno = new javax.swing.JTable();
        jLNombre = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jLApellido = new javax.swing.JLabel();
        jTApellido = new javax.swing.JTextField();
        jLDNI = new javax.swing.JLabel();
        jTDNI = new javax.swing.JTextField();
        jLDomicilio = new javax.swing.JLabel();
        jTDomicilio = new javax.swing.JTextField();
        jLTelefono = new javax.swing.JLabel();
        jTTelefono = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JButton();
        jBVolver = new javax.swing.JButton();
        jBEliminar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();
        jLFechaNac = new javax.swing.JLabel();
        dateChooser = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPAlumno.setBackground(new java.awt.Color(127, 175, 207));

        jLAlumnoTitulo.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLAlumnoTitulo.setForeground(new java.awt.Color(51, 51, 51));
        jLAlumnoTitulo.setText("ALUMNO");

        jTableAlumno.setBackground(new java.awt.Color(255, 253, 232));
        jTableAlumno.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTableAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Fecha Nac.", "Domicilio", "Teléfono", "Cod. Inscripción"
            }
        ));
        jTableAlumno.setGridColor(new java.awt.Color(0, 0, 0));
        jTableAlumno.setRowHeight(20);
        jScrollPane1.setViewportView(jTableAlumno);

        jLNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLNombre.setForeground(new java.awt.Color(51, 51, 51));
        jLNombre.setText("NOMBRE");

        jTNombre.setBackground(new java.awt.Color(255, 253, 232));
        jTNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLApellido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLApellido.setForeground(new java.awt.Color(51, 51, 51));
        jLApellido.setText("APELLIDO");

        jTApellido.setBackground(new java.awt.Color(255, 253, 232));
        jTApellido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLDNI.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLDNI.setForeground(new java.awt.Color(51, 51, 51));
        jLDNI.setText("DNI");

        jTDNI.setBackground(new java.awt.Color(255, 253, 232));
        jTDNI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLDomicilio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLDomicilio.setForeground(new java.awt.Color(51, 51, 51));
        jLDomicilio.setText("DOMICILIO");

        jTDomicilio.setBackground(new java.awt.Color(255, 253, 232));
        jTDomicilio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLTelefono.setForeground(new java.awt.Color(51, 51, 51));
        jLTelefono.setText("TELÉFONO");

        jTTelefono.setBackground(new java.awt.Color(255, 253, 232));
        jTTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jBGuardar.setBackground(new java.awt.Color(204, 204, 204));
        jBGuardar.setFont(new java.awt.Font("Ubuntu Light", 0, 12)); // NOI18N
        jBGuardar.setForeground(new java.awt.Color(51, 51, 51));
        jBGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/boton agregar.png"))); // NOI18N
        jBGuardar.setText("NUEVO");

        jBVolver.setBackground(new java.awt.Color(204, 204, 204));
        jBVolver.setFont(new java.awt.Font("Ubuntu Light", 0, 12)); // NOI18N
        jBVolver.setForeground(new java.awt.Color(51, 51, 51));
        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/volver.png"))); // NOI18N
        jBVolver.setText("ATRAS");
        jBVolver.setMaximumSize(new java.awt.Dimension(140, 57));
        jBVolver.setMinimumSize(new java.awt.Dimension(140, 57));

        jBEliminar.setBackground(new java.awt.Color(204, 204, 204));
        jBEliminar.setFont(new java.awt.Font("Ubuntu Light", 0, 12)); // NOI18N
        jBEliminar.setForeground(new java.awt.Color(51, 51, 51));
        jBEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/eliminar.png"))); // NOI18N
        jBEliminar.setText("BORRAR");
        jBEliminar.setMaximumSize(new java.awt.Dimension(140, 57));
        jBEliminar.setMinimumSize(new java.awt.Dimension(140, 57));

        jBModificar.setBackground(new java.awt.Color(204, 204, 204));
        jBModificar.setFont(new java.awt.Font("Ubuntu Light", 0, 12)); // NOI18N
        jBModificar.setForeground(new java.awt.Color(51, 51, 51));
        jBModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilities/editar.png"))); // NOI18N
        jBModificar.setText("EDITAR");

        jLFechaNac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLFechaNac.setForeground(new java.awt.Color(51, 51, 51));
        jLFechaNac.setText("FECHA NAC.");

        dateChooser.setFormat(2);
        dateChooser.setFieldFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        dateChooser.setLocale(new java.util.Locale("lt", "", ""));

        javax.swing.GroupLayout jPAlumnoLayout = new javax.swing.GroupLayout(jPAlumno);
        jPAlumno.setLayout(jPAlumnoLayout);
        jPAlumnoLayout.setHorizontalGroup(
            jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAlumnoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPAlumnoLayout.createSequentialGroup()
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLNombre)
                            .addComponent(jLDNI)
                            .addComponent(jLApellido)
                            .addComponent(jLFechaNac)
                            .addComponent(jLDomicilio)
                            .addComponent(jLTelefono))
                        .addGap(18, 18, 18)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPAlumnoLayout.createSequentialGroup()
                                .addComponent(jBGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(jBModificar)
                                .addGap(18, 18, 18)
                                .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLAlumnoTitulo))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPAlumnoLayout.setVerticalGroup(
            jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAlumnoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLAlumnoTitulo)
                .addGap(51, 51, 51)
                .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPAlumnoLayout.createSequentialGroup()
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDNI)
                            .addComponent(jTDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLNombre)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLApellido)
                            .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLFechaNac)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDomicilio)
                            .addComponent(jTDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLTelefono)
                            .addComponent(jTTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPAlumnoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBModificar)
                        .addComponent(jBEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public DateChooserCombo getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(DateChooserCombo dateChooser) {
        this.dateChooser = dateChooser;
    }

    public JButton getjBGuardar() {
        return jBGuardar;
    }

    public void setjBGuardar(JButton jBGuardar) {
        this.jBGuardar = jBGuardar;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTextField getjTApellido() {
        return jTApellido;
    }

    public void setjTApellido(JTextField jTApellido) {
        this.jTApellido = jTApellido;
    }

    public JTextField getjTDNI() {
        return jTDNI;
    }

    public void setjTDNI(JTextField jTDNI) {
        this.jTDNI = jTDNI;
    }

    public JTextField getjTDomicilio() {
        return jTDomicilio;
    }

    public void setjTDomicilio(JTextField jTDomicilio) {
        this.jTDomicilio = jTDomicilio;
    }

    public JTextField getjTNombre() {
        return jTNombre;
    }

    public void setjTNombre(JTextField jTNombre) {
        this.jTNombre = jTNombre;
    }

    public JTextField getjTTelefono() {
        return jTTelefono;
    }

    public void setjTTelefono(JTextField jTTelefono) {
        this.jTTelefono = jTTelefono;
    }

    public JTable getjTableAlumno() {
        return jTableAlumno;
    }

    public JButton getjBVolver() {
        return jBVolver;
    }

    public void setjTableAlumno(JTable jTableAlumno) {
        this.jTableAlumno = jTableAlumno;
    }

    public JButton getjBEliminar() {
        return jBEliminar;
    }

    public JButton getjBModificar() {
        return jBModificar;
    }

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
            java.util.logging.Logger.getLogger(ViewAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAlumno().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JButton jBEliminar;
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBVolver;
    private javax.swing.JLabel jLAlumnoTitulo;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLDNI;
    private javax.swing.JLabel jLDomicilio;
    private javax.swing.JLabel jLFechaNac;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLTelefono;
    private javax.swing.JPanel jPAlumno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTDNI;
    private javax.swing.JTextField jTDomicilio;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTextField jTTelefono;
    private javax.swing.JTable jTableAlumno;
    // End of variables declaration//GEN-END:variables
}
