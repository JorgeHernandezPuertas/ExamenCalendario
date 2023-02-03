/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author jorge
 */
public class Calendario {

    // Atributos
    private int[][] calendario;
    private int anio;
    private Month mes;

    // Constructor
    public Calendario(int anio, Month mes) {
        this.anio = anio;
        this.mes = mes;
        this.calendario = new int[6][7];
        // Relleno el calendario vacio
        rellenarCalendario();
    }

    // Método para rellenar el arrayCal
    private void rellenarCalendario() {
        LocalDate fechaMes = LocalDate.of(this.anio, this.mes, 1);
        int numDia = fechaMes.getDayOfWeek().getValue();
        for (int i = 0; i < this.calendario.length; i++) {
            // Si es la primera fila se varia donde se inicia en la semana
            if (i == 0) {
                for (int j = numDia - 1; j < this.calendario[i].length; j++) {
                    this.calendario[i][j] = j - numDia + 2;
                }
            } else {
                for (int j = 0; j < this.calendario[i].length; j++) {
                    // Si el numéro del dia es inferio al maximo del mes se pone
                    if (j + (i * 7) - numDia + 2 <= fechaMes.lengthOfMonth()) {
                        this.calendario[i][j] = j + (i * 7) - numDia + 2;
                    }
                }
            }

        }
    }

    // Getters 
    public int[][] getCalendario() {
        return calendario;
    }

    public int getAnio() {
        return anio;
    }

    public Month getMes() {
        return mes;
    }

    // Setters
    public void setCalendario(int[][] calendario) {
        this.calendario = calendario;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setMes(Month mes) {
        this.mes = mes;
    }

    // Métodos equals y hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.anio;
        hash = 97 * hash + Objects.hashCode(this.mes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Calendario other = (Calendario) obj;
        if (this.anio != other.anio) {
            return false;
        }
        return this.mes == other.mes;
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Creo la cabecera
        sb.append("L\tM\tX\tJ\tV\tS\tD\n");
        // Imprimo el calendario
        for (int i = 0; i < this.calendario.length; i++) {
            for (int j = 0; j < this.calendario[i].length; j++) {
                // Si es 0 dejo vacio
                if (this.calendario[i][j] == 0) {
                    sb.append(" \t");
                } else { // Si es un número lo pongo
                    sb.append(this.calendario[i][j] + "\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Método de clase del diaSemana
    public static String diaSemana(int diaMes, Calendario calendario) {
        Locale configSistema = Locale.getDefault();
        LocalDate fecha = LocalDate.of(calendario.anio, calendario.mes, 1);
        String diaString = "";
        // Si el dia está dentro del mes
        if (diaMes <= fecha.lengthOfMonth() && diaMes > 0) {
            for (int i = 0; i < calendario.calendario.length; i++) {
                for (int j = 0; j < calendario.calendario[i].length; j++) {
                    // Si el dia es el mismo
                    if (calendario.calendario[i][j] == diaMes) {
                        // Guardo el dia de la semana y lo paso a String en español
                        DayOfWeek diaSemana = DayOfWeek.of(j + 1);
                        diaString = diaSemana.getDisplayName(TextStyle.FULL, configSistema);
                        break;
                    }
                }
            }
        } else { // Si no esta dentro del mes lanzo la excepcion
            throw new IllegalArgumentException();
        }
        return diaString;
    }

    // Método de clase calendarioAnual
    public static Calendario[] calendarioAnual(int anio) {
        // Creo el array
        Calendario[] calendarioAnual = new Calendario[12];
        // Relleno el array
        for (int i = 0; i < calendarioAnual.length; i++) {
            calendarioAnual[i] = new Calendario(anio, Month.of(i + 1));
        }
        return calendarioAnual;
    }
}
