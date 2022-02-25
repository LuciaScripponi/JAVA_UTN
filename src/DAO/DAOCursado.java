package DAO;

import Model.Cursado;
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
public class DAOCursado extends DAOConexion {
    private ArrayList<Cursado> cursados;
    private Cursado cursado;

    public ArrayList<Cursado> traeNotasDAO() {
        cursados = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from Cursado");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                Cursado cursado = new Cursado();
                Model.Materia materia = new Model.Materia();
                cursado.setMateria(materia);
                Model.Alumno alumno = new Model.Alumno();
                cursado.setAlumno(alumno);
                cursado.getAlumno().setDni(hojadeResultados.getInt(1));
                cursado.getMateria().setCodMateria(hojadeResultados.getInt(2));
                cursado.setNota(hojadeResultados.getInt(3));
                cursados.add(cursado);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursados;
    }

    public ArrayList<String> traeDNIAlumnoDAO() {
        ArrayList<String> dni = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select alu_dni from alumno");
            ResultSet hojadeResultados = consulta.executeQuery();
            dni.add("");
            while (hojadeResultados.next()) {
                dni.add(Integer.toString(hojadeResultados.getInt(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dni;
    }

    public ArrayList<String> traeCodigoMateriaDAO() {
        ArrayList<String> codigo = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select mat_cod from materia");
            ResultSet hojadeResultados = consulta.executeQuery();
            codigo.add("");
            while (hojadeResultados.next()) {
                codigo.add(Integer.toString(hojadeResultados.getInt(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

    public Model.Materia traeMateriaDAO(int codigo) {
        Model.Materia materia = new Model.Materia();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from materia where mat_cod=?");
            this.consulta.setInt(1, codigo);
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                Profesor profesor = new Profesor();
                materia.setProfesor(profesor);
                materia.setCodMateria(hojadeResultados.getInt(1));
                materia.setNombreMateria(hojadeResultados.getString(2));
                materia.getProfesor().setDni(hojadeResultados.getInt(3));
            }

            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }

    public Model.Alumno traeAlumnoDAO(Integer dni) {
        Model.Alumno alumno = new Model.Alumno();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from alumno where alu_dni=?");
            this.consulta.setInt(1, dni);
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                alumno.setDni(hojadeResultados.getInt(1));
                alumno.setNombre(hojadeResultados.getString(2));
                alumno.setApellido(hojadeResultados.getString(3));
                alumno.setDomicilio(hojadeResultados.getString(4));
                alumno.setTelefono(hojadeResultados.getString(5));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }

    public boolean cargaDatosDAO(Model.Cursado cursado) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO cursado (cur_alu_dni, cur_mat_cod,cur_nota) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, cursado.getAlumno().getDni());
            preparedStmt.setInt(2, cursado.getMateria().getCodMateria());
            preparedStmt.setDouble(3, cursado.getNota());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean bajaDAO(JTable tabla) {
        int seleccion;
        cursados = new ArrayList(traeNotasDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM cursado WHERE cur_alu_dni = ? AND cur_mat_cod = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, cursados.get(seleccion).getAlumno().getDni());
            this.consulta.setInt(2, cursados.get(seleccion).getMateria().getCodMateria());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

       public boolean bajaConDni(int dni) {
        cursados = new ArrayList(traeNotasDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM cursado WHERE cur_alu_dni = ?");
            this.consulta.setInt(1, dni);
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean modificaDAO(Model.Cursado cursado) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE cursado SET cur_nota=? WHERE cur_alu_dni=? AND cur_mat_cod=?");
            preparedStmt.setDouble(1, cursado.getNota());
            preparedStmt.setInt(2, cursado.getAlumno().getDni());
            preparedStmt.setInt(3, cursado.getMateria().getCodMateria());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean notaRepetida(Model.Cursado cursado) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from cursado WHERE cur_alu_dni=? AND cur_mat_cod=?");
            this.consulta.setInt(1, cursado.getAlumno().getDni());
            this.consulta.setInt(2, cursado.getMateria().getCodMateria());
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                return true;
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
