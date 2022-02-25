package Model;

import DAO.DAOAlumno;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Alumno {

    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String domicilio;
    private String telefono;
    private int codigoInscripcion;
    private DAOAlumno DAOalumno = new DAOAlumno();

    //Constructor
    public Alumno() {
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

    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }
    
    //Metodos
    public boolean validaDNI(String a) {

        try {
            dni = Integer.parseInt(a);
            return false;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }

    public boolean dniRepetido(Alumno alumno) {
        return !DAOalumno.dniRepetidoDAO(alumno);
    }

    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cargaDatos(Alumno alumno) {
        return DAOalumno.cargaDatosDAO(alumno);
    }

    public ArrayList<Alumno> traeAlumnos() {
        return DAOalumno.traeAlumnosDAO();
    }

    public boolean baja(JTable tabla) {
        return DAOalumno.bajaDAO(tabla);
    }

    public boolean modifica(Alumno alumno) {
        return DAOalumno.modificaDAO(alumno);
    }

    public boolean updateCarreraAlumno(Alumno alumno) {
        return DAOalumno.updateCarrera(alumno);
    }

    public boolean bajaConCod(int inscCod) {
        return DAOalumno.bajaConCodDAO(inscCod);
    }
}
