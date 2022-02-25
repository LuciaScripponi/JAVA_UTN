package Controller;

import Model.Cursado;
import View.ViewCursado;
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
public class ControllerCursado implements ActionListener, MouseListener, ItemListener {

    private Model.Cursado cursado;
    private View.ViewCursado cursadoVista;
    private DefaultTableModel modeloTabla;
    private Model.Alumno alumno = new Model.Alumno();
    private Model.Materia materia = new Model.Materia();

    public ControllerCursado(Cursado cursado, ViewCursado cursadoVista) {
        this.cursado = cursado;
        this.cursadoVista = cursadoVista;
        this.cursadoVista.setVisible(true);
        this.llenarFilas(this.cursadoVista.getjTNotas());
        llenaComboBoxAlumno();
        this.cursadoVista.getjTAlumno().setEditable(false);
        llenaComboBoxMateria();
        this.cursadoVista.getjTMateria().setEditable(false);
        this.cursado.setAlumno(alumno);
        this.cursado.setMateria(materia);
        escucharBotones();
    }

    public void escucharBotones() {
        this.cursadoVista.getjBGuardar().addActionListener(this);
        this.cursadoVista.getjBEliminar().addActionListener(this);
        this.cursadoVista.getjBModificar().addActionListener(this);
        this.cursadoVista.getjTNotas().addMouseListener(this);
        this.cursadoVista.getjBVolver().addActionListener(this);
        this.cursadoVista.getjCBCodigoMateria().addItemListener(this);
        this.cursadoVista.getjCBDniAlumno().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.cursadoVista.getjBGuardar())) {

            if (cursado.validaCarga(this.cursadoVista.getjCBCodigoMateria().toString()) || cursado.validaCarga(this.cursadoVista.getjCBDniAlumno().toString()) || cursado.validaCarga(this.cursadoVista.getjTNota().getText())) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios!");
            } else if (cursado.validaNota(Integer.parseInt(this.cursadoVista.getjTNota().getText()))) {
                this.cursado.getAlumno().setDni(Integer.parseInt(this.cursadoVista.getjCBDniAlumno().getSelectedItem().toString()));
                this.cursado.getMateria().setCodMateria(Integer.parseInt(this.cursadoVista.getjCBCodigoMateria().getSelectedItem().toString()));
                this.cursado.setNota(Integer.parseInt(this.cursadoVista.getjTNota().getText()));
                if (cursado.notaRepetida(cursado)) {
                    if (this.cursado.cargaDatos(cursado)) {
                        JOptionPane.showMessageDialog(null, "Nota cargada!");
                    }
                    this.limpiarTabla(this.cursadoVista.getjTNotas());
                    llenarFilas(this.cursadoVista.getjTNotas());
                    limpiaCuadros();

                } else {
                    JOptionPane.showMessageDialog(null, "Nota repetida!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La nota debe estar entre 1 y 10");
            }
        } else if (evento.getSource().equals(this.cursadoVista.getjBEliminar())) {

            int fila = cursadoVista.getjTNotas().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.cursado.baja(this.cursadoVista.getjTNotas())) {
                    limpiarTabla(this.cursadoVista.getjTNotas());
                    llenarFilas(this.cursadoVista.getjTNotas());
                    JOptionPane.showMessageDialog(null, "Nota eliminada!");
                    limpiaCuadros();
                }
            }
        } else if (evento.getSource().equals(this.cursadoVista.getjBModificar())) {

            int fila = cursadoVista.getjTNotas().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.cursado.getAlumno().setDni(Integer.parseInt(this.cursadoVista.getjCBDniAlumno().getSelectedItem().toString()));
                this.cursado.getMateria().setCodMateria(Integer.parseInt(this.cursadoVista.getjCBCodigoMateria().getSelectedItem().toString()));
                this.cursado.setNota(Integer.parseInt(this.cursadoVista.getjTNota().getText()));
                if (this.cursado.modifica(cursado)) {
                    JOptionPane.showMessageDialog(null, "Nota modificada!");
                }
                this.limpiarTabla(this.cursadoVista.getjTNotas());
                llenarFilas(this.cursadoVista.getjTNotas());
                limpiaCuadros();
            }
        } else if (evento.getSource().equals(this.cursadoVista.getjBVolver())) {
            View.ViewMenu menuVista = new View.ViewMenu();
            Controller.ControllerMenu menuControlador = new ControllerMenu(menuVista);
            this.cursadoVista.dispose();
        }
    }

    public void llenaComboBoxAlumno() {
        ArrayList<String> dni = this.cursado.traeDNIAlumno();
        Iterator<String> dniIterator = dni.iterator();
        while (dniIterator.hasNext()) {
            this.cursadoVista.getjCBDniAlumno().addItem(dniIterator.next());
        }
    }

    public void llenaComboBoxMateria() {
        ArrayList<String> materia = this.cursado.traeCodigoMateria();
        Iterator<String> materiaIterator = materia.iterator();
        while (materiaIterator.hasNext()) {
            this.cursadoVista.getjCBCodigoMateria().addItem(materiaIterator.next());
        }
    }

    public void llenarFilas(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, dameColumnas());
        ArrayList<Model.Cursado> cursados;
        cursados = this.cursado.traeNotas();
        this.limpiarTabla(this.cursadoVista.getjTNotas());
        Object datos[] = new Object[3];
        if (cursados.size() > 0) {
            for (int i = 0; i < cursados.size(); i++) {
                datos[0] = cursados.get(i).getAlumno().getDni();
                datos[1] = cursados.get(i).getMateria().getCodMateria();
                datos[2] = cursados.get(i).getNota();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        cursados.clear();

    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public String[] dameColumnas() {
        String[] Columna = {"DNI Alumno", "Codigo Materia", "Nota"};
        return Columna;
    }

    public void limpiaCuadros() {
        this.cursadoVista.getjTNota().setText("");
        this.cursadoVista.getjCBCodigoMateria().setSelectedIndex(0);
        this.cursadoVista.getjCBCodigoMateria().setEnabled(true);
        this.cursadoVista.getjCBDniAlumno().setSelectedIndex(0);
        this.cursadoVista.getjCBDniAlumno().setEnabled(true);
        this.cursadoVista.getjTMateria().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.cursadoVista.getjTNotas().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.cursadoVista.getjCBDniAlumno().setSelectedItem(String.valueOf(this.cursadoVista.getjTNotas().getValueAt(fila, 0)));
                this.cursadoVista.getjCBDniAlumno().setEnabled(false);
                this.cursadoVista.getjCBCodigoMateria().setSelectedItem(String.valueOf(this.cursadoVista.getjTNotas().getValueAt(fila, 1)));
                this.cursadoVista.getjCBCodigoMateria().setEnabled(false);
                this.cursadoVista.getjTNota().setText(String.valueOf(this.cursadoVista.getjTNotas().getValueAt(fila, 2)));

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
        if (ie.getItemSelectable().equals(this.cursadoVista.getjCBCodigoMateria()) && !this.cursadoVista.getjCBCodigoMateria().getSelectedItem().equals("")) {
            int eleccion = Integer.parseInt(this.cursadoVista.getjCBCodigoMateria().getSelectedItem().toString());
            this.materia = this.cursado.traeMateria(eleccion);
            this.cursadoVista.getjTMateria().setText(this.materia.getNombreMateria());
        } else if (ie.getItemSelectable().equals(this.cursadoVista.getjCBDniAlumno()) && !this.cursadoVista.getjCBDniAlumno().getSelectedItem().equals("")) {
            Integer eleccion = Integer.parseInt(this.cursadoVista.getjCBDniAlumno().getSelectedItem().toString());
            this.alumno = this.cursado.traeAlumno(eleccion);
            this.cursadoVista.getjTAlumno().setText(this.alumno.getNombre() + " " + this.alumno.getApellido());
        }
    }
}
