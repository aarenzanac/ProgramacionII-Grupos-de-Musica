/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos_de_musica;

import dao.GestionDao;
import funciones_variadas.FuncionesVariadas;
import funciones_variadas.PideDatos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import models.Disco;

/**
 *
 * @author alex
 */
public class Grupos_de_Musica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        GestionDao gDao = new GestionDao();
              
        FuncionesVariadas.insertarDiscosEjemplo(gDao);
        
        FuncionesVariadas.mostrarMenu();
        
    }
    
    
}
