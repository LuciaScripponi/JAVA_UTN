package Model;

import DAO.DAOMateria;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Materia {

    private int codMateria;
    private String nombreMateria;
    private int dniProfesor;
    private DAOMateria DAOmateria = new DAOMateria();
    private Profesor profesor;

    //Constructor
    public Materia() {
    }
    
    //Getters y setters
    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(int dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public DAOMateria getDAOmateria() {
        return DAOmateria;
    }

    public void setDAOmateria(DAOMateria DAOmateria) {
        this.DAOmateria = DAOmateria;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    public ArrayList<Materia> traeMaterias() {
        return DAOmateria.traeMateriasDAO();
    }

    public ArrayList<String> traeDNI() {
        return DAOmateria.traeDNIProfesorDAO();
    }

    public Model.Profesor traeProfesor(Integer dni) {
        this.profesor = DAOmateria.traeProfesorDAO(dni);
        return this.profesor;
    }

    public boolean cargaDatos(Materia materia) {
        return DAOmateria.cargaDatosDAO(materia);
    }

    public boolean baja(JTable tabla) {
        return DAOmateria.bajaDAO(tabla);
    }

    public boolean modifica(Materia materia) {
        return DAOmateria.modificaDAO(materia);
    }

    public boolean cargaDniAMateria(Integer dni, int codigo) {
        return DAOmateria.cargaDniAMateriaDAO(dni, codigo);
    }
}
