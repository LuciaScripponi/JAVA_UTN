package Controller;

import Model.Alumno;
import Model.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class ControllerInscripcion implements ActionListener, MouseListener, ItemListener {

    private View.ViewInscripcion InscripcionVista;
    private Model.Inscripcion InscripcionModelo;
    private DefaultTableModel modeloTabla;
    Carrera carrera = new Carrera();
    Alumno alumno = new Alumno();

    public View.ViewInscripcion getInscripcionVista() {
        return InscripcionVista;
    }

    public ControllerInscripcion(View.ViewInscripcion vista, Model.Inscripcion modelo) {
        this.InscripcionVista = vista;
        this.InscripcionModelo = modelo;
        this.InscripcionVista.setVisible(true);
        llenarComboBox();
        llenarFilas(this.InscripcionVista.getjTinscripcion());
        escucharBotones();
        this.InscripcionModelo.setCarrera(carrera);
        this.InscripcionModelo.setAlumno(alumno);
    }

    public void escucharBotones() {
        this.InscripcionVista.getjBVolver().addActionListener(this);
        this.InscripcionVista.getjBGuardar().addActionListener(this);
        this.InscripcionVista.getjTinscripcion().addMouseListener(this);
        this.InscripcionVista.getjBEliminar().addActionListener(this);
        this.InscripcionVista.getjBModificar().addActionListener(this);
        this.InscripcionVista.getjCBCodigoCarrera().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.InscripcionVista.getjBGuardar())) {
            Alumno alu = InscripcionModelo.traeAlumno(splitearDniAlumno(InscripcionVista.getjCBDniAlumno().getSelectedItem().toString()));
            int inscripCod = alu.getCodigoInscripcion();
            if (inscripCod > 0) {
                JOptionPane.showMessageDialog(null, "Este Alumno Ya Posee Una Inscricpion A Carrera");
            } else if (revisarCampos() == true) {
                if (InscripcionModelo.validaCarga(this.InscripcionVista.getjTCodInscripcion().getText())) {
                    JOptionPane.showMessageDialog(null, "Codigo de Inscripción no puede estar vacío!");

                } else {
                    this.InscripcionModelo.setCodigoInscripcion(Integer.valueOf(this.InscripcionVista.getjTCodInscripcion().getText()));
                    this.InscripcionModelo.setNombre(this.splitearNombreAlumno(this.InscripcionVista.getjCBDniAlumno().getSelectedItem().toString()));
                    this.InscripcionModelo.setFecha(Date.valueOf(this.InscripcionVista.getDateChooser().getText()));
                    this.InscripcionModelo.setCodigoCarrera(this.splitearCodCarrera(this.InscripcionVista.getjCBCodigoCarrera().getSelectedItem().toString()));

                    alumno.setCodigoInscripcion(Integer.valueOf(InscripcionVista.getjTCodInscripcion().getText()));
                    alumno.setDni(splitearDniAlumno(InscripcionVista.getjCBDniAlumno().getSelectedItem().toString()));
                    alumno.updateCarreraAlumno(alumno);

                    if (this.InscripcionModelo.cargaDatos(InscripcionModelo)) {
                        JOptionPane.showMessageDialog(null, "Inscripcion cargada!");
                    }
                    this.limpiarTabla(this.InscripcionVista.getjTinscripcion());
                    llenarFilas(this.InscripcionVista.getjTinscripcion());
                    limpiaCuadros();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Vacios!");
            }
        } else if (evento.getSource().equals(this.InscripcionVista.getjBVolver())) {
            View.ViewMenu menuVista = new View.ViewMenu();
            ControllerMenu menuControlador = new ControllerMenu(menuVista);
            this.InscripcionVista.dispose();

        } else if (evento.getSource().equals(this.InscripcionVista.getjBEliminar())) {
            int fila = InscripcionVista.getjTinscripcion().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else if (this.InscripcionModelo.baja(this.InscripcionVista.getjTinscripcion())) {
                limpiarTabla(this.InscripcionVista.getjTinscripcion());
                llenarFilas(this.InscripcionVista.getjTinscripcion());
                JOptionPane.showMessageDialog(null, "Inscripcion eliminada!");
                limpiaCuadros();
            }

        } else if (evento.getSource().equals(this.InscripcionVista.getjBModificar())) {
            int fila = InscripcionVista.getjTinscripcion().getSelectedRow();
            if (revisarCampos() == true) {
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
                } else {
                    this.InscripcionModelo.setCodigoInscripcion(Integer.parseInt(this.InscripcionVista.getjTCodInscripcion().getText()));

                    if (this.InscripcionVista.getjCBCodigoCarrera().getSelectedItem().toString().equals("")) {
                        this.InscripcionModelo.getCarrera().setCodigoCarrera(0);
                    } else {
                        this.InscripcionModelo.setCodigoInscripcion(Integer.valueOf(this.InscripcionVista.getjTCodInscripcion().getText()));
                        this.InscripcionModelo.setNombre(this.splitearNombreAlumno(this.InscripcionVista.getjCBDniAlumno().getSelectedItem().toString()));
                        this.InscripcionModelo.setFecha(Date.valueOf(this.InscripcionVista.getDateChooser().getText()));
                        this.InscripcionModelo.setCodigoCarrera(this.splitearCodCarrera(this.InscripcionVista.getjCBCodigoCarrera().getSelectedItem().toString()));
                    }
                    if (this.InscripcionModelo.modifica(InscripcionModelo)) {
                        JOptionPane.showMessageDialog(null, "Inscripcion modificada!");
                    }
                    this.limpiarTabla(this.InscripcionVista.getjTinscripcion());
                    llenarFilas(this.InscripcionVista.getjTinscripcion());
                    limpiaCuadros();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Vacios!");
            }
        }
    }

    public void llenarComboBox() {

        InscripcionVista.getjCBDniAlumno().removeAllItems();
        InscripcionVista.getjCBDniAlumno().addItem("Seleccionar Alumno");
        for (int i = 0; i < alumno.traeAlumnos().size(); i++) {
            InscripcionVista.getjCBDniAlumno().addItem(String.valueOf(alumno.traeAlumnos().get(i).getDni()) + " - " + alumno.traeAlumnos().get(i).getNombre()
                    + " " + alumno.traeAlumnos().get(i).getApellido());
        }
        InscripcionVista.getjCBCodigoCarrera().removeAllItems();
        InscripcionVista.getjCBCodigoCarrera().addItem("Seleccionar Carrera");

        for (int i = 0; i < carrera.traeCarreras().size(); i++) {
            InscripcionVista.getjCBCodigoCarrera().addItem(String.valueOf(carrera.traeCarreras().get(i).getCodigoCarrera() + " - " + carrera.traeCarreras().get(i).getNombre()));
        }
    }

    public String[] dameColumnas() {
        String[] Columna = {"Cod. Inscripcion", "Nombre Alumno", "Fecha Insc.", "Cod. Carrera"};
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
        ArrayList<Model.Inscripcion> inscripciones;
        this.InscripcionVista.getjTCodInscripcion().setEditable(true);
        inscripciones = this.InscripcionModelo.traeInscripcion();
        this.limpiarTabla(this.InscripcionVista.getjTinscripcion());
        Object datos[] = new Object[4];
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                datos[0] = inscripciones.get(i).getCodigoInscripcion();
                datos[1] = inscripciones.get(i).getNombre();
                datos[2] = inscripciones.get(i).getFecha();
                datos[3] = inscripciones.get(i).getCodigoCarrera();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        inscripciones.clear();
    }

    public int splitearCodCarrera(String carreraCod) {
        String[] parts = carreraCod.split(" - ");
        String part1 = parts[0]; // Cod Carrera
        return Integer.valueOf(part1);
    }

    public int splitearDniAlumno(String alumnoDni) {
        String[] parts = alumnoDni.split(" - ");
        String part1 = parts[0]; // DNI
        return Integer.valueOf(part1);
    }

    public String splitearNombreAlumno(String alumnoNombre) {
        String[] parts = alumnoNombre.split(" - ");
        String part1 = parts[1]; //Nombre
        return part1;
    }

    public void limpiaCuadros() {
        this.InscripcionVista.getjTCodInscripcion().setText("");
        this.InscripcionVista.getjCBDniAlumno().setSelectedIndex(0);
        this.InscripcionVista.getjCBCodigoCarrera().setSelectedIndex(0);
    }

    public boolean revisarCampos() {
        if (this.InscripcionVista.getjTCodInscripcion().getText().isEmpty()
                || this.InscripcionVista.getjCBDniAlumno().getSelectedItem().equals("Seleccionar Alumno")
                || this.InscripcionVista.getjCBDniAlumno().getSelectedItem() == null
                || this.InscripcionVista.getjCBCodigoCarrera().getSelectedItem().equals("Seleccionar Carrera")
                || this.InscripcionVista.getjCBCodigoCarrera().getSelectedItem() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.InscripcionVista.getjTinscripcion().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.InscripcionVista.getjTCodInscripcion().setText(String.valueOf(this.InscripcionVista.getjTinscripcion().getValueAt(fila, 0)));

                for (int i = 0; i < alumno.traeAlumnos().size(); i++) {
                    if ((alumno.traeAlumnos().get(i).getNombre()
                            + " " + alumno.traeAlumnos().get(i).getApellido()).equals((this.InscripcionVista.getjTinscripcion().getValueAt(fila, 1).toString()))) {

                        String a = ((alumno.traeAlumnos().get(i).getDni()) + " - " + alumno.traeAlumnos().get(i).getNombre() + " " + alumno.traeAlumnos().get(i).getApellido());
                        this.InscripcionVista.getjCBDniAlumno().setSelectedItem(String.valueOf(a));
                    }
                }

                for (int i = 0; i < carrera.traeCarreras().size(); i++) {
                    if (String.valueOf(carrera.traeCarreras().get(i).getCodigoCarrera()).equals(String.valueOf(this.InscripcionVista.getjTinscripcion().getValueAt(fila, 3)))) {
                        String b = ((carrera.traeCarreras().get(i).getCodigoCarrera() + " - " + carrera.traeCarreras().get(i).getNombre()));
                        this.InscripcionVista.getjCBCodigoCarrera().setSelectedItem(String.valueOf(b));
                    }
                }
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

    @Override
    public void itemStateChanged(ItemEvent ie) {

    }
}
