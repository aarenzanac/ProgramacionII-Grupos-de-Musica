/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones_variadas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alex
 */
public class PideDatos {
    
    //Funcion para pedir un double entre 0.0 y 5.0 pasando un string como parámetro. Para introducir las notas.
    public static double pideDouble(String pregunta){
        //declaración e inicialización de variables
        Double numeroDouble = null;
        //mientras numero siga siendo 0 es que el número introducido no es correcto (decimal o texto) seguira preguntando
        while (numeroDouble == null){
            try{
                //muestra la pregunta introducida anteriormente y pregunta un número entero por teclado
                System.out.println(pregunta);
                Scanner entrada = new Scanner(System.in);
                numeroDouble = entrada.nextDouble();
                // capturamos el error en caso de que se introduzca un valor no valido (texto o decimal)
            }catch(InputMismatchException e){
                System.out.println("El número introducido no es válido. Por favor, introduazca caracteres numéricos enteros válidos.\n");
            }
        // retornamos numeroDoble    
        } return numeroDouble;
    }
    //Funcion que pide un entero. Para el menú.
    public static int pideEntero(){
        
        //declaramos la variable y la inicializamos a 0
        Integer numeroEntero = null;
        
        //mientras la variable sea 0 quiere decir que el número introducido no es correcto
        do{
            try{
                //solicitamos al usuario un número por teclado
                Scanner entrada = new Scanner(System.in);
                numeroEntero = entrada.nextInt();
                        
            //si el número no es correcto (decimal o texto por ejemplo) que muestre el siguiente mensaje de error y vuelta a empezar
            }catch (InputMismatchException e){            
                System.out.println("El dato introducido no es correcto. Por favor, introduzca un número vaĺido.\n");
            }
        }while (numeroEntero == null);
    //retorna el número entero introducido pero no lo muestra por pantalla    
    return numeroEntero;    
    }
    
    //Función que pide un entero entre 1 y 5 pasando un string como parámetro. Para pedir la posición en el array.
    public static int pideEntero(String cadena){
        int numero = 0;
        //mientras numero siga siendo 0 es que el número introducido no es correcto (decimal o texto) seguira preguntando
        while (numero == 0){
            try{
                //muestra la pregunta introducida anteriormente y pregunta un número entero por teclado
                System.out.println(cadena);
                Scanner entrada = new Scanner(System.in);
                numero = entrada.nextInt();
            // capturamos el error en caso de que se introduzca un valor no valido (texto o decimal)
            }catch(InputMismatchException e){
                System.out.println("El número introducido no es válido. Por favor, introduazca caracteres numéricos enteros válidos.\n");
            }
        // retornamos numero   
        } return numero;
    }
    
    //Creo la función de pedir String para la actividad 2
    public static String pideString(String cadena) throws IOException{
        System.out.println(cadena + "\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String valorIntroducido = br.readLine();
        return valorIntroducido;
    }
}
