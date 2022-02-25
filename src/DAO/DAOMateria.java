package DAO;

import Model.Materia;
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
public class DAOMateria extends DAOConexion {
    private Materia materia;
    private ArrayList<Materia> materias;

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<Materia> traeMateriasDAO() {
        materias = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM materia");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                materia = new Materia();
                materia.setCodMateria(hojadeResultados.getInt(1));
                materia.setNombreMateria(hojadeResultados.getString(2));
                materia.setDniProfesor(hojadeResultados.getInt(3));
                materias.add(materia);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }

    public ArrayList<String> traeDNIProfesorDAO() {
        ArrayList<String> dni = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select prof_dni from profesor");
            ResultSet hojadeResultados = consulta.executeQuery();
            dni.add("");
            while (hojadeResultados.next()) {
                dni.add(Integer.toString(hojadeResultados.getInt(1)));
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dni;
    }

    public boolean cargaDatosDAO(Materia materia) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO materia (mat_cod,mat_nombre, mat_prof_dni) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, materia.getCodMateria());
            preparedStmt.setString(2, materia.getNombreMateria());
            preparedStmt.setInt(3, materia.getProfesor().getDni());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean cargaDniAMateriaDAO(Integer dni, int codigo) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "UPDATE materia SET mat_prof_dni=? WHERE mat_cod=?";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, materia.getDniProfesor());
            preparedStmt.setInt(2, materia.getCodMateria());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean bajaDAO(JTable tabla) {
        int seleccion;
        materias = new ArrayList(traeMateriasDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM materia WHERE mat_cod=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, materias.get(seleccion).getCodMateria());
            consulta.executeUpdate();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(Materia materia) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE materia SET mat_nombre =?, mat_prof_dni=? WHERE mat_cod=?");
            preparedStmt.setString(1, materia.getNombreMateria());
            preparedStmt.setInt(2, materia.getProfesor().getDni());
            preparedStmt.setInt(3, materia.getCodMateria());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Model.Profesor traeProfesorDAO(Integer dni) {
        Model.Profesor profesor = new Model.Profesor();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM profesor WHERE prof_dni=?");
            this.consulta.setInt(1, dni);
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                profesor.setDni(hojadeResultados.getInt(1));
                profesor.setNombre(hojadeResultados.getString(2));
                profesor.setApellido(hojadeResultados.getString(3));
                profesor.setFechaNacimiento(hojadeResultados.getDate(4));
                profesor.setDomicilio(hojadeResultados.getString(5));
                profesor.setTelefono(hojadeResultados.getString(6));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesor;
    }
}
