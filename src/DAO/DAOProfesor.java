package DAO;

import Model.Profesor;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author lucia
 */
public class DAOProfesor extends DAOConexion {
    private ArrayList<Model.Profesor> profesores;
    private Model.Profesor profesor;

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<Model.Profesor> traeProfesoresDAO() {
        profesores = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM profesor");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                profesor = new Model.Profesor();
                profesor.setDni(hojadeResultados.getInt(1));
                profesor.setNombre(hojadeResultados.getString(2));
                profesor.setApellido(hojadeResultados.getString(3));
                profesor.setFechaNacimiento(hojadeResultados.getDate(4));
                profesor.setDomicilio(hojadeResultados.getString(5));
                profesor.setTelefono(hojadeResultados.getString(6));
                profesores.add(profesor);
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesores;
    }

    public Model.Profesor traeUNProfesorDAO(Integer dni) {
        Model.Profesor profe = new Model.Profesor();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM profesor WHERE prof_dni=?");
            this.consulta.setInt(1, dni);
            ResultSet hojadeResultados = consulta.executeQuery();
            profe.setDni(hojadeResultados.getInt(1));
            profe.setNombre(hojadeResultados.getString(2));
            profe.setApellido(hojadeResultados.getString(3));
            profe.setFechaNacimiento(hojadeResultados.getDate(4));
            profe.setDomicilio(hojadeResultados.getString(5));
            profe.setTelefono(hojadeResultados.getString(6));
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profe;
    }

    public boolean cargaDatosDAO(Model.Profesor profesor) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO profesor (prof_dni, prof_nombre,prof_apellido,prof_fec_nac,prof_domicilio, prof_telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, profesor.getDni());
            preparedStmt.setString(2, profesor.getNombre());
            preparedStmt.setString(3, profesor.getApellido());
            preparedStmt.setDate(4, profesor.getFechaNacimiento());
            preparedStmt.setString(5, profesor.getDomicilio());
            preparedStmt.setString(6, profesor.getTelefono());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean bajaDAO(JTable tabla) {
        int seleccion;
        profesores = new ArrayList(traeProfesoresDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("DELETE FROM profesor WHERE prof_dni=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, profesores.get(seleccion).getDni());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProfesor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(Model.Profesor profesor) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("UPDATE profesor SET prof_nombre=?,prof_apellido=?,prof_fec_nac=?,prof_domicilio=?, prof_telefono=? WHERE prof_dni=?");
            this.consulta.setString(1, profesor.getNombre());
            this.consulta.setString(2, profesor.getApellido());
            this.consulta.setDate(3, profesor.getFechaNacimiento());
            this.consulta.setString(4, profesor.getDomicilio());
            this.consulta.setString(5, profesor.getTelefono());
            this.consulta.setInt(6, profesor.getDni());
            this.consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOProfesor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean dniRepetidoDAO(Model.Profesor profesor) {

        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM profesor WHERE prof_dni = ?");
            this.consulta.setInt(1, profesor.getDni());
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                return true;
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
