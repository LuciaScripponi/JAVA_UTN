package Model;

import DAO.DAOCursado;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class Cursado {

    private int alumnoDni;
    private int codigoMateria;
    private double nota;
    private Alumno alumno;
    private Materia materia;
    private DAO.DAOCursado cursadoDAO = new DAO.DAOCursado();
    
    //Constructor
    public Cursado() {
    }

    public Cursado(double nota, Alumno alumno, Materia materia) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }
    
    //Getters y setters
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public DAOCursado getCursadoDAO() {
        return cursadoDAO;
    }

    public void setCursadoDAO(DAOCursado cursadoDAO) {
        this.cursadoDAO = cursadoDAO;
    }

    public int getAlumnoDni() {
        return alumnoDni;
    }

    public void setAlumnoDni(int alumnoDni) {
        this.alumnoDni = alumnoDni;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    //Metodos
    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Cursado> traeNotas() {
        return cursadoDAO.traeNotasDAO();
    }

    public ArrayList<String> traeDNIAlumno() {
        return cursadoDAO.traeDNIAlumnoDAO();
    }

    public Model.Alumno traeAlumno(Integer dni) {
        this.alumno = cursadoDAO.traeAlumnoDAO(dni);
        return this.alumno;
    }

    public ArrayList<String> traeCodigoMateria() {
        return cursadoDAO.traeCodigoMateriaDAO();
    }

    public Model.Materia traeMateria(int codigo) {
        this.materia = cursadoDAO.traeMateriaDAO(codigo);
        return this.materia;
    }

    public boolean cargaDatos(Model.Cursado cursado) {
        return cursadoDAO.cargaDatosDAO(cursado);
    }

    public boolean baja(JTable tabla) {
        return cursadoDAO.bajaDAO(tabla);
    }
    
    public boolean bajaConDni(int dni) {
        return cursadoDAO.bajaConDni(dni);
    }

    public boolean modifica(Model.Cursado cursado) {
        return cursadoDAO.modificaDAO(cursado);
    }

    public boolean notaRepetida(Model.Cursado nota) {
        return !cursadoDAO.notaRepetida(nota);
    }

    public boolean validaNota(int nota) {
        if (nota >= 1 && nota <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
