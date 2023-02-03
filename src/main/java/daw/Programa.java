/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class Programa {

    public static void main(String[] args) {
        
        // Pido el año que quiera el usuario
        int anio = pedirAnio();
        
        // Imprimo todos los calendarios de ese año
        Calendario[] calendarioAnual = Calendario.calendarioAnual(anio);
        imprimirCalendarios(calendarioAnual);
        
        // Pruebo el método diaSemana
        System.out.println("El dia 23 de Diciembre cae en:");
        System.out.println(Calendario.diaSemana(23, calendarioAnual[11]));
        // Puebo que lanza la excepcion al introducir un dia que no esta en el mes
        System.out.println("El dia 33 de Febrero cae en: (tiene que dar excepción por la prueba)");
        System.out.println(Calendario.diaSemana(33, calendarioAnual[1]));
        
        
    }

    // Atributos de clase
    private static Scanner teclado = new Scanner(System.in);
    private static final int ANIO_MIN = 1950, ANIO_MAX = 2050;

    // Método para pedir por consola al usuario un año entre 1950 y 2050
    private static int pedirAnio() {
        int anio = 0;
        do {
            try {
                System.out.println("Introduce el año del que quieras tener los "
                        + "calendarios (entre 1950 y 2050):");
                anio = teclado.nextInt();
                if (anio < ANIO_MIN || anio > ANIO_MAX) {
                    System.out.println("Ese año no está dentro del rango");
                }
            }catch (InputMismatchException ime){
                System.out.println("No has introducido un entero.");
            }
            System.out.println("------------------------------------------------");
            teclado.nextLine(); // Limpio buffer
        } while (anio < ANIO_MIN || anio > ANIO_MAX);
        return anio;
    }
    
    // Método para imprimir los calendarios del año por consola
    private static void imprimirCalendarios(Calendario[] calendarioAnual){
        Locale config = Locale.getDefault(); // Cojo la configuracion del sistema
        for (int i = 0;i<calendarioAnual.length;i++){
            // Imprimo el mes que corresponda
            String mes = Month.of(i + 1).getDisplayName(TextStyle.FULL, config);
            System.out.println("------------------------------------------------");
            System.out.println(mes + ":" );
            System.out.println(calendarioAnual[i].toString());
            System.out.println("------------------------------------------------");
        }
    }
}
