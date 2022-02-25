package Model;

import DAO.DAOInscripcion;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Inscripcion {

    private int codigoInscripcion;
    private String nombre;
    private Date fecha;
    private int codigoCarrera;
    private DAOInscripcion inscripcionDAO = new DAOInscripcion();
    private Carrera carrera;
    private Alumno alumno;

    //Contructor
    public Inscripcion() {
    }

    public Inscripcion(int codigoInscripcion, String nombre, Date fecha, int codigoCarrera) {
        this.codigoInscripcion = codigoInscripcion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.codigoCarrera = codigoCarrera;
    }
    
    //Getters y setters
    public DAOInscripcion getInscripcionDAO() {
        return inscripcionDAO;
    }

    public void setInscripcionDAO(DAOInscripcion inscripcionDAO) {
        this.inscripcionDAO = inscripcionDAO;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }
    
    //Metodos
    public boolean validaDNI(String a) {
        try {
            Integer.parseInt(a);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Inscripcion> traeInscripcion() {
        return inscripcionDAO.traeInscripcionsDAO();
    }

    public ArrayList<String> traeCOD() {
        return inscripcionDAO.traeCODcarreraDAO();
    }

    public Model.Carrera traeCarrera(Integer cod) {
        this.carrera = inscripcionDAO.traeCarreraDAO(cod);
        return this.carrera;
    }

    public Model.Alumno traeAlumno(Integer dni) {
        this.alumno = inscripcionDAO.traeAlumnoDAO(dni);
        return this.alumno;
    }

    public ArrayList<String> traeDNIAlumno() {
        return inscripcionDAO.traeDNIAlumnoDAO();
    }

    public boolean cargaDatos(Inscripcion inscripcion) {
        return inscripcionDAO.cargaDatosDAO(inscripcion);
    }

    public boolean baja(JTable tabla) {
        return inscripcionDAO.bajaDAO(tabla);
    }
 
    public boolean modifica(Inscripcion inscripcion) {
        return inscripcionDAO.modificaDAO(inscripcion);
    }

    public boolean cargaCodInscripcion(Integer cod, int codigo) {
        return inscripcionDAO.cargaCodAInscripcionDAO(cod, codigo);
    }
}
