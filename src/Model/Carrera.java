package Model;

import DAO.DAOCarrera;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Carrera {

    private int codigoCarrera;
    private String nombre;
    private String duracion;
    private DAOCarrera DAOcarrera = new DAOCarrera();
    
    //Constructor
    public Carrera() {
    }
    
    //Getters y setters
    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
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

    public boolean cargaDatos(Carrera carrera) {
        return DAOcarrera.cargaDatosDAO(carrera);
    }

    public ArrayList<Carrera> traeCarreras() {
        return DAOcarrera.traeCarrerasDAO();
    }

    public boolean baja(JTable tabla) {
        return DAOcarrera.bajaDAO(tabla);
    }

    public boolean modifica(Carrera carrera) {
        return DAOcarrera.modificaDAO(carrera);
    }

    public Model.Carrera traeUNCarrera(Integer dni) {
        return DAOcarrera.traeUNCarreraDAO(dni);
    }

    public boolean dniRepetido(Carrera carrera) {
        return !DAOcarrera.dniRepetidoDAO(carrera);
    }
}
