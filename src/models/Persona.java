package models;

import java.math.BigDecimal;

public class Persona {
    private String nombre;
    private String cedula;
    private int edad;
    private double auxilio;
    private boolean esDesplazado;
    private int estrato;
    private int estado;   //1 = activo, 2 = inactivo

    public Persona(String nombre, int edad, String cedula, double auxilio, boolean esDesplazado, int estrato) {
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.auxilio = auxilio;
        this.esDesplazado = esDesplazado;
        this.estrato = estrato;
        this.estado = 1;
    }

    public Persona() {
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAuxilio() {
        return auxilio;
    }

    public void setAuxilio(double auxilio) {
        this.auxilio = auxilio;
    }

    public boolean isEsDesplazado() {
        return esDesplazado;
    }

    public void setEsDesplazado(boolean esDesplazado) {
        this.esDesplazado = esDesplazado;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", edad=" + edad +
                ", auxilio=" + auxilio +
                ", esDesplazado=" + esDesplazado +
                ", estrato=" + estrato +
                '}';
    }
}
