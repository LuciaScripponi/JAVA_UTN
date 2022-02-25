package Controller;

import View.ViewAlumno;
import View.ViewMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lucia
 */
public class ControllerMenu implements ActionListener {
    private ViewMenu menu;

    public ControllerMenu(View.ViewMenu menu) {
        this.menu = menu;
        menu.setVisible(true);
        escucharBotones();
    }

    public void escucharBotones() {
        this.menu.getjBAlumno().addActionListener(this);
        this.menu.getjBProfesor().addActionListener(this);
        this.menu.getjBMateria().addActionListener(this);
        this.menu.getjBCursado().addActionListener(this);
        this.menu.getjBInscripcion().addActionListener(this);
        this.menu.getjBCarrera().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(this.menu.getjBAlumno())) {
            Model.Alumno alumnoModelo = new Model.Alumno();
            ViewAlumno alumnoVista = new View.ViewAlumno();
            ControllerAlumno alumnoControlador = new Controller.ControllerAlumno(alumnoModelo, alumnoVista);
            this.menu.dispose();
        } else if (evento.getSource().equals(this.menu.getjBMateria())) {
            Model.Materia materiaModelo = new Model.Materia();
            View.ViewMateria materiaVista = new View.ViewMateria();
            ControllerMateria materiaControlador = new Controller.ControllerMateria(materiaVista, materiaModelo);
            this.menu.dispose();
        } else if (evento.getSource().equals(this.menu.getjBProfesor())) {
            Model.Profesor profesorModelo = new Model.Profesor();
            View.ViewProfesor profesorVista = new View.ViewProfesor();
            ControllerProfesor profesorControlador = new Controller.ControllerProfesor(profesorVista, profesorModelo);
            this.menu.dispose();
        } else if (evento.getSource().equals(this.menu.getjBCarrera())) {
            Model.Carrera carreraModelo = new Model.Carrera();
            View.ViewCarrera carreraVista = new View.ViewCarrera();
            ControllerCarrera carreraControlador = new Controller.ControllerCarrera(carreraVista, carreraModelo);
            this.menu.dispose();
        } else if (evento.getSource().equals(this.menu.getjBCursado())) {
            Model.Cursado cursadoModelo = new Model.Cursado();
            View.ViewCursado cursadoVista = new View.ViewCursado();
            ControllerCursado cursadoControlador = new Controller.ControllerCursado(cursadoModelo, cursadoVista);
            this.menu.dispose();
        } else if (evento.getSource().equals(this.menu.getjBInscripcion())) {
            Model.Inscripcion inscripcionModelo = new Model.Inscripcion();
            View.ViewInscripcion inscripcionVista = new View.ViewInscripcion();
            ControllerInscripcion inscripcionControlador = new Controller.ControllerInscripcion(inscripcionVista, inscripcionModelo);
            this.menu.dispose();
        }
    }
}
