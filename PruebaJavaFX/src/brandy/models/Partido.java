package brandy.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Partido implements Comparable<Partido>, Serializable {

    private String equipo_Local;
    private String equipo_Visitante;
    private int resultado_Lo;
    private int resultado_Vi;
    private Division division;
    private String resultado;
    private Date date;
    //protected Date fechaDate;

    // protected Fecha objDate;

    public Partido(String equipo_Local, String equipo_Visitante,
                   int resultado_Lo, int resultado_Vi, Division division, String resultado,
                   Date date) {
        super();
        this.equipo_Local = equipo_Local;
        this.equipo_Visitante = equipo_Visitante;
        this.resultado_Lo = resultado_Lo;
        this.resultado_Vi = resultado_Vi;
        this.division = division;
        this.resultado = resultado;
        this.date = date;

    }

    public String getEquipo_Local() {
        return equipo_Local;
    }

    public void setEquipo_Local(String equipo_Local) {
        this.equipo_Local = equipo_Local;
    }

    public String getEquipo_Visitante() {
        return equipo_Visitante;
    }

    public void setEquipo_Visitante(String equipo_Visitante) {
        this.equipo_Visitante = equipo_Visitante;
    }

    public int getResultado_Lo() {
        return resultado_Lo;
    }

    public void setResultado_Lo(int resultado_Lo) {
        this.resultado_Lo = resultado_Lo;
    }

    public int getResultado_Vi() {
        return resultado_Vi;
    }

    public void setResultado_Vi(int resultado_Vi) {
        this.resultado_Vi = resultado_Vi;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getRes() {
        return resultado;
    }

    public void setRes(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public Date getDate() {
        return date;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Devuelve la fecha convertida en string para imprimir la propiedad en la tabla
     * @return
     */
    public String getFechaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaS = sdf.format(date);
        return fechaS;
    }

    public String calcular_Resultado() {
        String sA = String.valueOf(resultado_Lo);
        String sB = String.valueOf(resultado_Vi);

        resultado = sA + "-" + sB;
        // int resultado = Integer.parseInt(res);
        return resultado;
    }
    @Override
    public String toString() {
        return "Partido [equipo_Local=" + equipo_Local + ", equipo_Visitante="
                + equipo_Visitante + ", resultado_Lo=" + resultado_Lo
                + ", resultado_Vi=" + resultado_Vi + ", a=" + division
                + ", resultado=" + resultado + ", date=" + date
                + ", fechaDate=]";
    }

    @Override
    public int compareTo(Partido o) {
        return 0;
    }
}