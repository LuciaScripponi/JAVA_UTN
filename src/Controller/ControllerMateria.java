package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class ControllerMateria implements ActionListener, MouseListener, ItemListener {

    private View.ViewMateria materiaVista;
    private Model.Materia materiaModelo;
    private DefaultTableModel modeloTabla;
    Model.Profesor profesor = new Model.Profesor();

    public View.ViewMateria getMateriaVista() {
        return materiaVista;
    }

    public ControllerMateria(View.ViewMateria vista, Model.Materia modelo) {
        this.materiaVista = vista;
        this.materiaModelo = modelo;
        this.materiaVista.setVisible(true);
        llenaComboBox();
        llenarFilas(this.materiaVista.getjTable1());
        escucharBotones();
        this.materiaModelo.setProfesor(profesor);
        this.materiaVista.getjTProfesorNombre().setEditable(false);
    }

    public void escucharBotones() {
        this.materiaVista.getjBVolver().addActionListener(this);
        this.materiaVista.getjBGuardar().addActionListener(this);
        this.materiaVista.getjTable1().addMouseListener(this);
        this.materiaVista.getjBEliminar().addActionListener(this);
        this.materiaVista.getjBModificar().addActionListener(this);
        this.materiaVista.getjCBDNIProfesor().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.materiaVista.getjBGuardar())) {
            if (materiaModelo.validaCarga(this.materiaVista.getjTNombre().getText())) {
                JOptionPane.showMessageDialog(null, "Nombre no puede estar vacío!");
            } else {
                this.materiaModelo.setCodMateria(Integer.valueOf(this.materiaVista.getjTCodigo().getText()));
                this.materiaModelo.setNombreMateria(this.materiaVista.getjTNombre().getText());
                this.materiaModelo.getProfesor().setDni(Integer.parseInt(this.materiaVista.getjCBDNIProfesor().getSelectedItem().toString()));
                if (this.materiaVista.getjCBDNIProfesor().getSelectedItem().toString().equals("")) {
                }
                if (this.materiaModelo.cargaDatos(materiaModelo)) {
                    JOptionPane.showMessageDialog(null, "Materia cargada!");
                }
                this.limpiarTabla(this.materiaVista.getjTable1());
                llenarFilas(this.materiaVista.getjTable1());
                limpiaCuadros();
            }
        } else if (evento.getSource().equals(this.materiaVista.getjBVolver())) {
            View.ViewMenu menuVista = new View.ViewMenu();
            ControllerMenu menuControlador = new ControllerMenu(menuVista);
            this.materiaVista.dispose();
        } else if (evento.getSource().equals(this.materiaVista.getjBEliminar())) {
            int fila = materiaVista.getjTable1().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.materiaModelo.baja(this.materiaVista.getjTable1())) {
                    limpiarTabla(this.materiaVista.getjTable1());
                    llenarFilas(this.materiaVista.getjTable1());
                    JOptionPane.showMessageDialog(null, "Materia eliminada!");
                    limpiaCuadros();
                }
            }
        } else if (evento.getSource().equals(this.materiaVista.getjBModificar())) {
            int fila = materiaVista.getjTable1().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.materiaModelo.setCodMateria(Integer.parseInt(this.materiaVista.getjTCodigo().getText()));
                this.materiaModelo.setNombreMateria(this.materiaVista.getjTNombre().getText());
                if (this.materiaVista.getjCBDNIProfesor().getSelectedItem().toString().equals("")) {
                    this.materiaModelo.getProfesor().setDni(0);
                } else {
                    this.materiaModelo.getProfesor().setDni(Integer.parseInt(this.materiaVista.getjCBDNIProfesor().getSelectedItem().toString()));
                }
                if (this.materiaModelo.modifica(materiaModelo)) {
                    JOptionPane.showMessageDialog(null, "Materia modificada!");
                }
                this.limpiarTabla(this.materiaVista.getjTable1());
                llenarFilas(this.materiaVista.getjTable1());
                limpiaCuadros();
            }
        }
    }

    public void llenaComboBox() {
        ArrayList<String> dni = this.materiaModelo.traeDNI();
        Iterator<String> dniIterator = dni.iterator();
        while (dniIterator.hasNext()) {
            this.materiaVista.getjCBDNIProfesor().addItem(dniIterator.next());
        }
    }

    public String[] dameColumnas() {
        String[] Columna = {"Cod. Materia", "Nombre", "DNI Profesor"};
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
        ArrayList<Model.Materia> materias;
        this.materiaVista.getjTCodigo().setEditable(true);
        materias = this.materiaModelo.traeMaterias();
        this.limpiarTabla(this.materiaVista.getjTable1());
        Object datos[] = new Object[3];
        if (materias.size() > 0) {
            for (int i = 0; i < materias.size(); i++) {
                datos[0] = materias.get(i).getCodMateria();
                datos[1] = materias.get(i).getNombreMateria();
                datos[2] = materias.get(i).getDniProfesor();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        materias.clear();
    }

    public void limpiaCuadros() {
        this.materiaVista.getjTNombre().setText("");
        this.materiaVista.getjTCodigo().setText("");
        this.materiaVista.getjCBDNIProfesor().setSelectedIndex(0);
        this.materiaVista.getjTProfesorNombre().setText("");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.materiaVista.getjTable1().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.materiaVista.getjTCodigo().setEditable(false);
                this.materiaVista.getjTCodigo().setText(String.valueOf(this.materiaVista.getjTable1().getValueAt(fila, 0)));
                this.materiaVista.getjTNombre().setText(String.valueOf(this.materiaVista.getjTable1().getValueAt(fila, 1)));
                this.materiaVista.getjCBDNIProfesor().setSelectedItem(String.valueOf(this.materiaVista.getjTable1().getValueAt(fila, 2)));
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
        if (ie.getItemSelectable().equals(this.materiaVista.getjCBDNIProfesor()) && !this.materiaVista.getjCBDNIProfesor().getSelectedItem().equals("")) {
            Integer eleccion = Integer.parseInt(this.materiaVista.getjCBDNIProfesor().getSelectedItem().toString());
            this.profesor = this.materiaModelo.traeProfesor(eleccion);
            this.materiaVista.getjTProfesorNombre().setText(this.profesor.getNombre() + " " + this.profesor.getApellido());
        }
    }
}
