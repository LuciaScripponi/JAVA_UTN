package DAO;

import Model.Inscripcion;
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
public class DAOInscripcion extends DAOConexion {
    private Inscripcion inscripcion;
    private ArrayList<Inscripcion> inscripciones;

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public ArrayList<Inscripcion> traeInscripcionsDAO() {
        inscripciones = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM inscripcion");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setCodigoInscripcion(hojadeResultados.getInt(1));
                inscripcion.setNombre(hojadeResultados.getString(2));
                inscripcion.setFecha(hojadeResultados.getDate(3));
                inscripcion.setCodigoCarrera(hojadeResultados.getInt(4));
                inscripciones.add(inscripcion);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscripciones;
    }

    public ArrayList<String> traeCODcarreraDAO() {
        ArrayList<String> cod = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select car_cod from carrera");
            ResultSet hojadeResultados = consulta.executeQuery();
            cod.add("");
            while (hojadeResultados.next()) {
                cod.add(Integer.toString(hojadeResultados.getInt(1)));
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cod;
    }

    public boolean cargaDatosDAO(Inscripcion inscripcion) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO inscripcion (insc_cod,insc_nombre, insc_fecha, insc_car_cod) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, inscripcion.getCodigoInscripcion());
            preparedStmt.setString(2, inscripcion.getNombre());
            preparedStmt.setDate(3, inscripcion.getFecha());
            preparedStmt.setInt(4, inscripcion.getCodigoCarrera());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean cargaCodAInscripcionDAO(Integer cod, int codigo) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "UPDATE inscripcion SET insc_car_cod=? WHERE insc_cod=?";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, inscripcion.getCodigoCarrera());
            preparedStmt.setInt(2, inscripcion.getCodigoInscripcion());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean bajaDAO(JTable tabla) {
        int seleccion;
        inscripciones = new ArrayList(traeInscripcionsDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM inscripcion WHERE insc_cod=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, inscripciones.get(seleccion).getCodigoInscripcion());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(Inscripcion inscripcion) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE inscripcion SET insc_nombre =?, insc_fecha=?, insc_car_cod=? WHERE insc_cod=?");
            preparedStmt.setString(1, inscripcion.getNombre());
            preparedStmt.setDate(2, inscripcion.getFecha());
            preparedStmt.setInt(3, inscripcion.getCodigoCarrera());
            preparedStmt.setInt(4, inscripcion.getCodigoInscripcion());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Model.Carrera traeCarreraDAO(Integer cod) {
        Model.Carrera carrera = new Model.Carrera();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM carrera WHERE car_cod=?");
            this.consulta.setInt(1, cod);
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                carrera.setCodigoCarrera(hojadeResultados.getInt(1));
                carrera.setNombre(hojadeResultados.getString(2));
                carrera.setDuracion(hojadeResultados.getString(3));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOInscripcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carrera;
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
                alumno.setFechaNacimiento(hojadeResultados.getDate(4));
                alumno.setDomicilio(hojadeResultados.getString(5));
                alumno.setTelefono(hojadeResultados.getString(6));
                alumno.setCodigoInscripcion(hojadeResultados.getInt(7));
            }

            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCursado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
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
}
