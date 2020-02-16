/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import funciones_variadas.FuncionesVariadas;
import funciones_variadas.PideDatos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Disco;



/**
 *
 * @author alex
 */
public class GestionDao {
    
    private final ObjectContainer db;
    
    public GestionDao(){
         
        db = Db4oEmbedded.openFile("GestionDiscos");
     }
    
    public void insertarDisco(Disco d, GestionDao gDao){
        boolean existe = comprobarSiExisteDisco(d, gDao);
        if(existe == false){
            db.store(d);
            System.out.println("Disco insertado correctamente.");
            System.out.println(d.toString());
        }else{
            System.out.println("El disco indicado ya existe, no se introducirá.\n");
        }
     }
    
    public ArrayList<Disco> seleccionarTodosDiscos(GestionDao gDao){
        ArrayList<Disco> listaDiscos = new ArrayList<Disco>();
        Query q = db.query(); //Creamos la sentencia query
        q.constrain(Disco.class); //Asignamos la condición
        ObjectSet resultado = q.execute(); //Ejecutamos la sentencia y recuperamos el resultado
        
        while(resultado.hasNext()){
            Disco d = (Disco) resultado.next();
            listaDiscos.add(d);
        }
        return listaDiscos;
        
    }
    
    public void cerrarDB4O(){
         db.close();
         
    }
    
    public void borrarDB(){
        
         File f = new File("GestionDiscos");
         f.delete();
    }
    
    public ArrayList<Disco> seleccionarDiscosPorGrupo() throws IOException{
        String nombreGrupo = PideDatos.pideString("Introduzca el nombre del grupo a filtrar: \n").toUpperCase();
        ArrayList<Disco> listaDiscosPorGrupo = new ArrayList<Disco>();
        Query q = db.query(); //Creamos la sentencia query
        q.constrain(Disco.class); //Asignamos la condición
        q.descend("nombreGrupo").constrain(nombreGrupo);
        ObjectSet resultado = q.execute(); //Ejecutamos la sentencia y recuperamos el resultado
        
        if(resultado.hasNext()){
            while(resultado.hasNext()){
            Disco d = (Disco) resultado.next();
            listaDiscosPorGrupo.add(d);
            }
            System.out.println("A continuación se muestran todos los discos del grupo " + nombreGrupo + ":\n");
            return listaDiscosPorGrupo;
        }else{
            System.out.println("No existen grupos con ese nombre.\n");
        return listaDiscosPorGrupo;
        }
        
    }
     
    public void modificarDisco(Disco discoAntiguo, Disco discoNuevo, GestionDao gDao){
        boolean existe = comprobarSiExisteDisco(discoAntiguo, gDao);
        if(!discoAntiguo.getNombreGrupo().equals(discoNuevo.getNombreGrupo())){
            ObjectSet resultado = db.queryByExample(discoAntiguo);
            Disco d = (Disco) resultado.next();
            d.setNombreGrupo(discoNuevo.getNombreGrupo());
            db.store(d);
            System.out.println("Disco modificado con éxito.\n");
        }else if(!discoAntiguo.getTitulo().equals(discoNuevo.getTitulo())){
            ObjectSet resultado = db.queryByExample(discoAntiguo);
            Disco d = (Disco) resultado.next();
            d.setTitulo(discoNuevo.getTitulo());
            db.store(d);
            System.out.println("Disco modificado con éxito.\n");
        }else if(!discoAntiguo.getEstilo().equals(discoNuevo.getEstilo())){
            ObjectSet resultado = db.queryByExample(discoAntiguo);
            Disco d = (Disco) resultado.next();
            d.setEstilo(discoNuevo.getEstilo());
            db.store(d);
            System.out.println("Disco modificado con éxito.\n");
        }else if(discoAntiguo.getNumeroCanciones() != discoNuevo.getNumeroCanciones()){
            ObjectSet resultado = db.queryByExample(discoAntiguo);
            Disco d = (Disco) resultado.next();
            d.setNumeroCanciones(discoNuevo.getNumeroCanciones());
            db.store(d);
            System.out.println("Disco modificado con éxito.\n");
        }else{
            ObjectSet resultado = db.queryByExample(discoAntiguo);
            Disco d = (Disco) resultado.next();
            d.setAñoPublicacion(discoNuevo.getAñoPublicacion());
            db.store(d);
            System.out.println("Disco modificado con éxito.\n");
        }
    }
    
    public void eliminarDisco(Disco discoEliminar, GestionDao gDao){
        boolean existe = comprobarSiExisteDisco(discoEliminar, gDao);
        if(existe){Query q = db.query();
            q.constrain(Disco.class);
            q.descend("nombreGrupo").constrain(discoEliminar.getNombreGrupo()).and(q.descend("titulo").constrain(discoEliminar.getTitulo()));
            ObjectSet resultado = q.execute();
            Disco d = (Disco)resultado.next();
            db.delete(d);
            System.out.println("Disco eliminado con éxito.\n");
        }else{
            System.out.println("El disco indicado no existe.\n");
        }
    }
    
    public boolean comprobarSiExisteDisco(Disco discoEliminar, GestionDao gDao){
        boolean existe = false;
        ArrayList<Disco> listaCompleta = seleccionarTodosDiscos(gDao);
        for(int i = 0; i < listaCompleta.size(); i++){
            if(listaCompleta.get(i).getNombreGrupo().equals(discoEliminar.getNombreGrupo()) && listaCompleta.get(i).getTitulo().equals(discoEliminar.getTitulo())){
                return true;
            }
        }
        return existe;
    }
    
    public ArrayList<Disco> seleccionarDiscosPorEstilo() throws IOException{
        String nombreEstilo = PideDatos.pideString("Introduzca el nombre del estilo a filtrar: \n").toUpperCase();
        ArrayList<Disco> listaDiscosPorEstilo = new ArrayList<Disco>();
        Query q = db.query(); //Creamos la sentencia query
        q.constrain(Disco.class); //Asignamos la condición
        q.descend("estilo").constrain(nombreEstilo);
        ObjectSet resultado = q.execute(); //Ejecutamos la sentencia y recuperamos el resultado
        
        if(resultado.hasNext()){
            while(resultado.hasNext()){
            Disco d = (Disco) resultado.next();
            listaDiscosPorEstilo.add(d);
            }
            System.out.println("A continuación se muestran todos los discos de estilo" + nombreEstilo + ":\n");
            return listaDiscosPorEstilo;
        }else{
            System.out.println("No existen discos de el estilo indicado.\n");
        return listaDiscosPorEstilo;
        }
    }
    
    public ArrayList<Disco> seleccionarDiscosPorAño() throws IOException{
        int año = PideDatos.pideEntero("Introduzca el año de lanzamiento tope a filtrar: \n");
        ArrayList<Disco> listaDiscosPorAño = new ArrayList<Disco>();
        Query q = db.query(); //Creamos la sentencia query
        q.constrain(Disco.class); //Asignamos la condición
        q.descend("añoPublicacion").constrain(año).smaller();
        q.descend("añoPublicacion").orderAscending();
        ObjectSet resultado = q.execute(); //Ejecutamos la sentencia y recuperamos el resultado
        
        if(resultado.hasNext()){
            while(resultado.hasNext()){
            Disco d = (Disco) resultado.next();
            listaDiscosPorAño.add(d);
            }
            System.out.println("A continuación se muestran todos los discos anteriores al año " + año + ":\n");
            return listaDiscosPorAño;
        }else{
            System.out.println("No existen discos anteriores al año indicado.\n");
        return listaDiscosPorAño;
        }
    }
    
    public ArrayList<Disco> seleccionarDiscosPorEstiloyCancion() throws IOException{
        String nombreEstilo = PideDatos.pideString("Introduzca el nombre del estilo a filtrar: \n").toUpperCase();
        int numeroCanciones = PideDatos.pideEntero("Introduzca el número de canciones de los discos a mostrar: \n");
        ArrayList<Disco> listaDiscosPorEstiloyCancion = new ArrayList<Disco>();
        Query q = db.query(); 
        q.constrain(Disco.class);
        q.descend("estilo").constrain(nombreEstilo). and (q.descend("numeroCanciones").constrain(numeroCanciones).greater());
        ObjectSet resultado = q.execute();
        
        if(resultado.hasNext()){
            while(resultado.hasNext()){
            Disco d = (Disco) resultado.next();
            listaDiscosPorEstiloyCancion.add(d);
            }
            System.out.println("A continuación se muestran todos los discos del estilo " + nombreEstilo + " con mas de " + numeroCanciones + ":\n");
            return listaDiscosPorEstiloyCancion;
        }else{
            System.out.println("No existen discos con las características indicadas.\n");
        return listaDiscosPorEstiloyCancion;
        }
    }
   
}
