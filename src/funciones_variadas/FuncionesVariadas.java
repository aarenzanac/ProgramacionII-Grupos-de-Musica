/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones_variadas;

import dao.GestionDao;
import static funciones_variadas.PideDatos.pideEntero;
import static funciones_variadas.PideDatos.pideString;
import java.io.IOException;
import java.util.ArrayList;
import models.Disco;

/**
 *
 * @author alex
 */
public class FuncionesVariadas{
    
    public static void mostrarMenu() throws IOException{
    
        System.out.println("Bienvenido a la base de datos de grupos de música.\n");
        
            GestionDao gDao = new GestionDao();                                
            int opcion = 9;
            do{
                System.out.println("\n¿Que desea hacer ahora?: \n\n"
                + "A continuación elija una de las siguientes opciones: \n\n"
                       + "1 - Crear nuevo disco.\n"
                       + "2 - Mostrar todos los discos.\n"
                       + "3 - Mostrar todos los discos de un grupo en concreto.\n"
                       + "4 - Modificar un disco.\n"
                       + "5 - Eliminar un disco.\n" 
                       + "6 - Mostrar todos los discos de un estilo en concreto.\n"
                       + "7 - Mostrar todos los discos anteriores a un año en concreto.\n"
                       + "8 - Mostrar todos los discos de un estilo con mas de X canciones.\n"
                       + "9 - Salir.\n");
                       
            opcion = PideDatos.pideEntero();
            
            switch (opcion){
                case 1:
                    System.out.println("Ha elegido crear un nuevo disco.\n");
                    gDao.insertarDisco(FuncionesVariadas.crearObjetoDisco(), gDao);
                    continue;
                    
                case 2:
                    System.out.println("Ha elegido mostrar todos los discos.\n");
                    mostrarDiscos(gDao.seleccionarTodosDiscos(gDao));
                    continue;
                    
                case 3:
                    System.out.println("Ha elegido mostrar todos los discos de un grupo en concreto.\n");
                    mostrarDiscos(gDao.seleccionarDiscosPorGrupo());
                    continue;
                    
                case 4:
                    System.out.println("Ha elegido modificar un disco.\n");
                    System.out.println("Estos son todos los discos disponibles: \n");
                    FuncionesVariadas.mostrarDiscos(gDao.seleccionarTodosDiscos(gDao));
                    Disco discoAntiguo = discoSeleccionadoUsuario(gDao);
                    Disco discoNuevo = mostrarMenuCrearDiscoAtributoModificado(discoAntiguo);
                    gDao.modificarDisco(discoAntiguo, discoNuevo, gDao);
                    continue;
                    
                case 5:
                    System.out.println("Ha elegido eliminar un disco.\n");
                    System.out.println("Estos son todos los discos disponibles: \n");
                    FuncionesVariadas.mostrarDiscos(gDao.seleccionarTodosDiscos(gDao));
                    gDao.eliminarDisco(discoSeleccionadoUsuario(gDao), gDao);
                    continue;
                    
                case 6:
                    System.out.println("Ha elegido mostrar todos los discos de un estilo en concreto.\n");
                    mostrarDiscos(gDao.seleccionarDiscosPorEstilo());
                    continue;
                case 7:
                    System.out.println("Ha elegiso mostrar todos los discos anteriores a un año en concreto.\n");
                    mostrarDiscos(gDao.seleccionarDiscosPorAño());
                    continue;
                case 8:
                    System.out.println("Ha elegido mostrar todos los discos de un estilo con mas de X canciones.\n");
                    mostrarDiscos(gDao.seleccionarDiscosPorEstiloyCancion());
                    continue;
                case 9:
                    System.out.println("Ha elegido la opción salir. Espero verle pronto. Hasta luego.");
                    gDao.cerrarDB4O();
                    gDao.borrarDB();
                    break;
                    
                default:
                    System.out.println("La opción introducida no es correcta. Elija una opción del 1 al 8, por favor.");
                    System.out.println("-----------------------------------------------------------------------");
            } 
                       
           } while (opcion != 9);
        
    }
    
    public static Disco mostrarMenuCrearDiscoAtributoModificado(Disco discoAntiguo) throws IOException{
        int opcion = 6;
        Disco discoNuevo = new Disco(discoAntiguo.getNombreGrupo(), discoAntiguo.getTitulo(), discoAntiguo.getEstilo(), discoAntiguo.getNumeroCanciones(), discoAntiguo.getAñoPublicacion());
            do{
                System.out.println("\n¿Que atributo desea modificar?: \n\n"
                       + "1 - Nombre del grupo.\n"
                       + "2 - Titulo del disco.\n"
                       + "3 - Estilo.\n"
                       + "4 - Año de lanzamiento.\n"
                       + "5 - Número de canciones.\n");
                       
            opcion = PideDatos.pideEntero();
            
            switch (opcion){
                case 1:
                    System.out.println("Ha elegido modificar en nombre de grupo.\n");
                    discoNuevo.setNombreGrupo(PideDatos.pideString("Introduzca el nuevo nombre del grupo:\n").toUpperCase());
                    return discoNuevo;
                    
                case 2:
                    System.out.println("Ha elegido modificar el título.\n");
                    discoNuevo.setTitulo(PideDatos.pideString("Introduzca el nuevo titulo del disco:\n").toUpperCase());
                    return discoNuevo;
                    
                case 3:
                    System.out.println("Ha elegido modificar el estilo.\n");
                    discoNuevo.setEstilo(PideDatos.pideString("Introduzca el nuevo estilo:\n").toUpperCase());
                    return discoNuevo;
                    
                case 4:
                    System.out.println("Ha elegido modificar el año de lanzamiento.\n");
                    discoNuevo.setAñoPublicacion(PideDatos.pideEntero("Introduzca el nuevo año de lanzamiento:\n"));
                    return discoNuevo;
                    
                case 5:
                    System.out.println("Ha elegido modificar el numero de canciones\n");
                    discoNuevo.setNumeroCanciones(PideDatos.pideEntero("Introduzca el nuevo número de canciones:\n"));
                    return discoNuevo;
                                       
                default:
                    System.out.println("La opción introducida no es correcta. Elija una opción del 1 al 5, por favor.");
                    System.out.println("-----------------------------------------------------------------------");
            } 
                       
           } while (opcion < 1 || opcion > 5);
            
        return discoNuevo;
    }
  
    public static void insertarDiscosEjemplo(GestionDao gDao){
        Disco d1 = new Disco("EXTREMODURO", "IROS TODOS A TOMAR POR CULO", "ROCK", 14, 1997);
        Disco d2 = new Disco("EXTREMODURO", "AGILA", "ROCK", 13, 1996);
        Disco d3 = new Disco("BARRICADA", "NOCHE DE ROCK AND ROLL", "ROCK", 8, 1983);
        Disco d4 = new Disco("ALEJANDRO SANZ", "EL ALMA AL AIRE", "POP", 10, 2000);
        Disco d5 = new Disco("ALEJANDRO SANZ", "BASICO", "POP", 10, 1994);
        Disco d6 = new Disco("PEREZA", "APROXIMACIONES", "POP", 16, 2007);
        Disco d7 = new Disco("SFDK", "2001 ODISEA EN EL LODO", "RAP", 15, 2003);
        Disco d8 = new Disco("SFDK", "ORIGINAL RAP", "RAP", 12, 2006);
        
        gDao.insertarDisco(d1, gDao);
        gDao.insertarDisco(d2, gDao);
        gDao.insertarDisco(d3, gDao);
        gDao.insertarDisco(d4, gDao);
        gDao.insertarDisco(d5, gDao);
        
        gDao.cerrarDB4O();
        
    }
    
    public static Disco crearObjetoDisco() throws IOException{
        Disco d = new Disco();
        d.setNombreGrupo(pideString("Inserte el nombre del grupo; ").toUpperCase());
        d.setTitulo(pideString("Inserte el título del disco: ").toUpperCase());
        d.setEstilo(pideString("Introduzca el estilo: ").toUpperCase());
        d.setNumeroCanciones(pideEntero("Introduzca el número de canciones: "));
        d.setAñoPublicacion(pideEntero("Introduzca el año de publicación: "));

        return d;
    }
    
    public static void mostrarDiscos(ArrayList<Disco> listaDiscos){
        for(int i = 0; i < listaDiscos.size(); i++){
            System.out.println(listaDiscos.get(i).toString());
        }
    }
    
    public static Disco discoSeleccionadoUsuario(GestionDao gDao) throws IOException{
        Disco d = new Disco();
        d.setNombreGrupo(PideDatos.pideString("Introduzca el nombre del grupo autor del disco: \n").toUpperCase());
        d.setTitulo(PideDatos.pideString("Introduzca el nombre del título del disco:").toUpperCase());
        
        Disco discoExtraido = new Disco();
        ArrayList<Disco> listaCompleta = gDao.seleccionarTodosDiscos(gDao);
        for(int i = 0; i < listaCompleta.size(); i++){
            if(listaCompleta.get(i).getNombreGrupo().equals(d.getNombreGrupo()) && listaCompleta.get(i).getTitulo().equals(d.getTitulo())){
                discoExtraido.setNombreGrupo(listaCompleta.get(i).getNombreGrupo());
                discoExtraido.setTitulo(listaCompleta.get(i).getTitulo());
                discoExtraido.setEstilo(listaCompleta.get(i).getEstilo());
                discoExtraido.setNumeroCanciones(listaCompleta.get(i).getNumeroCanciones());
                discoExtraido.setAñoPublicacion(listaCompleta.get(i).getAñoPublicacion());
            }
        }
        return discoExtraido;
    }
    
    
}
