package Controller;

import View.ViewMenu;

public class Main {

/**
 *
 * @author lucia
 */
    public static void main(String[] args) {      
        ViewMenu menu = new ViewMenu();
        ControllerMenu menuControlador = new ControllerMenu(menu);   
    }    
}
