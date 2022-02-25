package Model;

import DAO.DAOProfesor;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Profesor {

    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String domicilio;
    private String telefono;
    private DAOProfesor DAOprofesor = new DAOProfesor();

    //Constructor
    public Profesor() {
    }

    //Getters y setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Metodos
    public boolean validaDNI(String a) {
        try {
            Integer.parseInt(a);
            return false;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }

    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cargaDatos(Profesor profesor) {

        return DAOprofesor.cargaDatosDAO(profesor);
    }

    public ArrayList<Profesor> traeProfesores() {
        return DAOprofesor.traeProfesoresDAO();
    }

    public boolean baja(JTable tabla) {
        return DAOprofesor.bajaDAO(tabla);
    }

    public boolean modifica(Profesor profesor) {
        return DAOprofesor.modificaDAO(profesor);
    }

    public Model.Profesor traeUNProfesor(Integer dni) {
        return DAOprofesor.traeUNProfesorDAO(dni);
    }

    public boolean dniRepetido(Profesor profesor) {
        return !DAOprofesor.dniRepetidoDAO(profesor);
    }
}
