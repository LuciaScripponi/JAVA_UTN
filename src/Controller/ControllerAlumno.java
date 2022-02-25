package Controller;

import Model.Cursado;
import Model.Inscripcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class ControllerAlumno implements ActionListener, MouseListener {

    private Model.Alumno alumnoModelo;
    private View.ViewAlumno alumnoVista;
    private DefaultTableModel modeloTabla;
    private Inscripcion inscripcion = new Inscripcion();
    private Cursado cursado = new Cursado();

    public ControllerAlumno(Model.Alumno modelo, View.ViewAlumno vista) {
        this.alumnoModelo = modelo;
        this.alumnoVista = vista;
        this.alumnoVista.setVisible(true);
        this.llenarFilas(this.alumnoVista.getjTableAlumno());
        escucharBotones();
    }

    public void escucharBotones() {
        this.alumnoVista.getjBGuardar().addActionListener(this);
        this.alumnoVista.getjBVolver().addActionListener(this);
        this.alumnoVista.getjBEliminar().addActionListener(this);
        this.alumnoVista.getjTableAlumno().addMouseListener(this);
        this.alumnoVista.getjBModificar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource().equals(this.alumnoVista.getjBGuardar())) {
            if (alumnoModelo.validaCarga(this.alumnoVista.getjTNombre().getText()) || alumnoModelo.validaCarga(this.alumnoVista.getjTApellido().getText()) || alumnoModelo.validaCarga(this.alumnoVista.getjTDNI().getText())) {

                JOptionPane.showMessageDialog(null, "DNI, nombre y apellido son obligatorios");

            } else if (alumnoModelo.validaDNI(this.alumnoVista.getjTDNI().getText())) {
                JOptionPane.showMessageDialog(null, "DNI inválido!");

            } else {

                this.alumnoModelo.setDni(Integer.parseInt(this.alumnoVista.getjTDNI().getText()));
                this.alumnoModelo.setNombre(this.alumnoVista.getjTNombre().getText());
                this.alumnoModelo.setApellido(this.alumnoVista.getjTApellido().getText());
                this.alumnoModelo.setFechaNacimiento(Date.valueOf(this.alumnoVista.getDateChooser().getText()));
                this.alumnoModelo.setDomicilio(this.alumnoVista.getjTDomicilio().getText());
                this.alumnoModelo.setTelefono(this.alumnoVista.getjTTelefono().getText());
                if (alumnoModelo.dniRepetido(alumnoModelo)) {
                    if (this.alumnoModelo.cargaDatos(alumnoModelo)) {
                        JOptionPane.showMessageDialog(null, "Alumno cargado!");
                    }
                    this.limpiarTabla(this.alumnoVista.getjTableAlumno());
                    llenarFilas(this.alumnoVista.getjTableAlumno());
                    limpiaCuadros();
                } else {
                    JOptionPane.showMessageDialog(null, "Alumno repetido!");
                }
            }
        } else if (evento.getSource().equals(this.alumnoVista.getjBVolver())) {
            View.ViewMenu menuVista = new View.ViewMenu();
            ControllerMenu menuControlador = new ControllerMenu(menuVista);
            this.alumnoVista.dispose();

        } else if (evento.getSource().equals(this.alumnoVista.getjBEliminar())) {

            int fila = alumnoVista.getjTableAlumno().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Atención! Tambien Eliminará la Inscripción y el Cursado Del Alumno.", "Desea Eliminar?", JOptionPane.YES_NO_OPTION) == 0) {
                    int cursadoDni = Integer.valueOf(alumnoVista.getjTableAlumno().getValueAt(fila, 0).toString());
                    cursado.bajaConDni(cursadoDni);
                    int inscCod = Integer.valueOf(alumnoVista.getjTableAlumno().getValueAt(fila, 6).toString());

                    alumnoModelo.bajaConCod(inscCod);

                    alumnoModelo.baja(this.alumnoVista.getjTableAlumno());
                    limpiarTabla(this.alumnoVista.getjTableAlumno());
                    llenarFilas(this.alumnoVista.getjTableAlumno());
                    JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente");
                    limpiaCuadros();
                    this.alumnoVista.getjTDNI().setEditable(true);
                }
            }

        } else if (evento.getSource().equals(this.alumnoVista.getjBModificar())) {

            int fila = alumnoVista.getjTableAlumno().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.alumnoModelo.setDni(Integer.parseInt(this.alumnoVista.getjTDNI().getText()));
                this.alumnoModelo.setNombre(this.alumnoVista.getjTNombre().getText());
                this.alumnoModelo.setApellido(this.alumnoVista.getjTApellido().getText());
                this.alumnoModelo.setFechaNacimiento(Date.valueOf(this.alumnoVista.getDateChooser().getText()));
                this.alumnoModelo.setDomicilio(this.alumnoVista.getjTDomicilio().getText());
                this.alumnoModelo.setTelefono(this.alumnoVista.getjTTelefono().getText());
                if (this.alumnoModelo.modifica(alumnoModelo)) {
                    JOptionPane.showMessageDialog(null, "Alumno modificado!");
                }
                this.limpiarTabla(this.alumnoVista.getjTableAlumno());
                llenarFilas(this.alumnoVista.getjTableAlumno());
                limpiaCuadros();
                this.alumnoVista.getjTDNI().setEditable(true);
            }
        }
    }

    public String[] dameColumnas() {
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha Nac.", "Domicilio", "Telefono", "Cod. Inscripción"};
        return Columna;
    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public void llenarFilas(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, dameColumnas());
        ArrayList<Model.Alumno> alumnos;
        alumnos = this.alumnoModelo.traeAlumnos();
        this.limpiarTabla(this.alumnoVista.getjTableAlumno());
        Object datos[] = new Object[7];
        if (alumnos.size() > 0) {
            for (int i = 0; i < alumnos.size(); i++) {
                datos[0] = alumnos.get(i).getDni();
                datos[1] = alumnos.get(i).getNombre();
                datos[2] = alumnos.get(i).getApellido();
                datos[3] = alumnos.get(i).getFechaNacimiento();
                datos[4] = alumnos.get(i).getDomicilio();
                datos[5] = alumnos.get(i).getTelefono();
                datos[6] = alumnos.get(i).getCodigoInscripcion();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        alumnos.clear();
    }

    public void limpiaCuadros() {
        this.alumnoVista.getjTNombre().setText("");
        this.alumnoVista.getjTApellido().setText("");
        this.alumnoVista.getjTDNI().setText("");
        this.alumnoVista.getjTDomicilio().setText("");
        this.alumnoVista.getjTTelefono().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.alumnoVista.getjTableAlumno().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.alumnoVista.getjTDNI().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 0)));
                this.alumnoVista.getjTDNI().setEditable(false);
                this.alumnoVista.getjTNombre().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 1)));
                this.alumnoVista.getjTApellido().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 2)));
                this.alumnoVista.getDateChooser().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 3)));
                this.alumnoVista.getjTDomicilio().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 4)));
                this.alumnoVista.getjTTelefono().setText(String.valueOf(this.alumnoVista.getjTableAlumno().getValueAt(fila, 5)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
