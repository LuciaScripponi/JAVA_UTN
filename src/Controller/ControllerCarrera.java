package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class ControllerCarrera implements ActionListener, MouseListener {

    private View.ViewCarrera carrerasVista;
    private Model.Carrera carrerasModelo;
    private DefaultTableModel modeloTabla;

    public ControllerCarrera(View.ViewCarrera vista, Model.Carrera modelo) {
        this.carrerasVista = vista;
        this.carrerasModelo = modelo;
        this.carrerasVista.setVisible(true);
        llenarFilas(this.carrerasVista.getjTableCarrera());
        escucharBotones();
    }

    public void escucharBotones() {
        this.carrerasVista.getjBGuardar().addActionListener(this);
        this.carrerasVista.getjBVolver().addActionListener(this);
        this.carrerasVista.getjBEliminar().addActionListener(this);
        this.carrerasVista.getjTableCarrera().addMouseListener(this);
        this.carrerasVista.getjBModificar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.carrerasVista.getjBGuardar())) {
            if (carrerasModelo.validaCarga(this.carrerasVista.getjTNombreCarrera().getText()) || carrerasModelo.validaCarga(this.carrerasVista.getjTDuracion().getText()) || carrerasModelo.validaCarga(this.carrerasVista.getjTCodCarrera().getText())) {

                JOptionPane.showMessageDialog(null, "DNI, nombre y apellido son obligatorios");

            } else if (carrerasModelo.validaDNI(this.carrerasVista.getjTCodCarrera().getText())) {
                JOptionPane.showMessageDialog(null, "DNI inválido!");
            } else {
                this.carrerasModelo.setCodigoCarrera(Integer.parseInt(this.carrerasVista.getjTCodCarrera().getText()));
                this.carrerasModelo.setNombre(this.carrerasVista.getjTNombreCarrera().getText());
                this.carrerasModelo.setDuracion(this.carrerasVista.getjTDuracion().getText());
                if (carrerasModelo.dniRepetido(carrerasModelo)) {
                    if (this.carrerasModelo.cargaDatos(carrerasModelo)) {
                        JOptionPane.showMessageDialog(null, "Carrera cargada!");
                    }
                    this.limpiarTabla(this.carrerasVista.getjTableCarrera());
                    llenarFilas(this.carrerasVista.getjTableCarrera());
                    limpiaCuadros();
                } else {
                    JOptionPane.showMessageDialog(null, "Carrera repetida!");
                }
            }
        } else if (evento.getSource().equals(this.carrerasVista.getjBVolver())) {
            View.ViewMenu menuVista = new View.ViewMenu();
            ControllerMenu menuControlador = new ControllerMenu(menuVista);
            this.carrerasVista.dispose();

        } else if (evento.getSource().equals(this.carrerasVista.getjBEliminar())) {
            int fila = carrerasVista.getjTableCarrera().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                if (this.carrerasModelo.baja(this.carrerasVista.getjTableCarrera())) {
                    limpiarTabla(this.carrerasVista.getjTableCarrera());
                    llenarFilas(this.carrerasVista.getjTableCarrera());
                    JOptionPane.showMessageDialog(null, "Carrera eliminada!");
                    limpiaCuadros();
                    this.carrerasVista.getjTCodCarrera().setEditable(true);
                }
            }
        } else if (evento.getSource().equals(this.carrerasVista.getjBModificar())) {
            int fila = carrerasVista.getjTableCarrera().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccione Una Fila!");
            } else {
                this.carrerasModelo.setCodigoCarrera(Integer.valueOf(this.carrerasVista.getjTCodCarrera().getText()));
                this.carrerasModelo.setNombre(this.carrerasVista.getjTNombreCarrera().getText());
                this.carrerasModelo.setDuracion(this.carrerasVista.getjTDuracion().getText());

                if (this.carrerasModelo.modifica(carrerasModelo)) {
                    JOptionPane.showMessageDialog(null, "Carrera modificada!");
                }
                this.limpiarTabla(this.carrerasVista.getjTableCarrera());
                llenarFilas(this.carrerasVista.getjTableCarrera());
                limpiaCuadros();
                this.carrerasVista.getjTCodCarrera().setEditable(true);
            }
        }
    }

    public String[] dameColumnas() {
        String[] Columna = {"Cod. Carrera", "Nombre", "Duración"};
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
        ArrayList<Model.Carrera> carreras;
        carreras = this.carrerasModelo.traeCarreras();
        this.limpiarTabla(this.carrerasVista.getjTableCarrera());
        Object datos[] = new Object[3];
        if (carreras.size() > 0) {
            for (int i = 0; i < carreras.size(); i++) {
                datos[0] = carreras.get(i).getCodigoCarrera();
                datos[1] = carreras.get(i).getNombre();
                datos[2] = carreras.get(i).getDuracion();
                modeloTabla.addRow(datos);
            }
        }

        tabla.setModel(modeloTabla);
        carreras.clear();
    }

    public void limpiaCuadros() {
        this.carrerasVista.getjTNombreCarrera().setText("");
        this.carrerasVista.getjTDuracion().setText("");
        this.carrerasVista.getjTCodCarrera().setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.carrerasVista.getjTableCarrera().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.carrerasVista.getjTCodCarrera().setText(String.valueOf(this.carrerasVista.getjTableCarrera().getValueAt(fila, 0)));
                this.carrerasVista.getjTCodCarrera().setEditable(false);
                this.carrerasVista.getjTNombreCarrera().setText(String.valueOf(this.carrerasVista.getjTableCarrera().getValueAt(fila, 1)));
                this.carrerasVista.getjTDuracion().setText(String.valueOf(this.carrerasVista.getjTableCarrera().getValueAt(fila, 2)));

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
