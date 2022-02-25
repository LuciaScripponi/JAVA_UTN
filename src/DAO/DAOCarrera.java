package DAO;

import Model.Carrera;
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
public class DAOCarrera extends DAOConexion {
    private ArrayList<Model.Carrera> carreras;
    private Model.Carrera carrera;

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }

    public ArrayList<Model.Carrera> traeCarrerasDAO() {
        carreras = new ArrayList();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM carrera");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                carrera = new Model.Carrera();
                carrera.setCodigoCarrera(hojadeResultados.getInt(1));
                carrera.setNombre(hojadeResultados.getString(2));
                carrera.setDuracion(hojadeResultados.getString(3));
                carreras.add(carrera);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreras;
    }

    public Model.Carrera traeUNCarreraDAO(Integer cod_car) {
        Model.Carrera carrera = new Model.Carrera();
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM carrera WHERE car_cod=?");
            this.consulta.setInt(1, cod_car);
            ResultSet hojadeResultados = consulta.executeQuery();
            carrera.setCodigoCarrera(hojadeResultados.getInt(1));
            carrera.setNombre(hojadeResultados.getString(2));
            carrera.setDuracion(hojadeResultados.getString(3));
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carrera;
    }

    public boolean cargaDatosDAO(Model.Carrera carrera) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO carrera (car_cod, car_nombre,car_duracion) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, carrera.getCodigoCarrera());
            preparedStmt.setString(2, carrera.getNombre());
            preparedStmt.setString(3, carrera.getDuracion());
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
        carreras = new ArrayList(traeCarrerasDAO());
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("DELETE FROM carrera WHERE car_cod=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setInt(1, carreras.get(seleccion).getCodigoCarrera());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCarrera.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(Model.Carrera carrera) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("UPDATE carrera SET car_nombre=?,car_duracion=? WHERE car_cod=?");
            this.consulta.setString(1, carrera.getNombre());
            this.consulta.setString(2, carrera.getDuracion());
            this.consulta.setInt(3, carrera.getCodigoCarrera());
            this.consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOCarrera.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean dniRepetidoDAO(Model.Carrera carrera) {
        try {
            this.conectar("127.0.0.1", "sga2021", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SELECT * FROM carrera WHERE car_cod = ?");
            this.consulta.setInt(1, carrera.getCodigoCarrera());
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
