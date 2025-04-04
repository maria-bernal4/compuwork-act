package com.compuwork.modelo;

import java.time.LocalDate;

public class EmpleadoPermanente extends Empleado {
    private double bonificacionAntiguedad;
    private int aniosServicio;

    public EmpleadoPermanente(int id, String nombre, String apellido, String dni, 
                            LocalDate fechaContratacion, double salarioBase, 
                            double bonificacionAntiguedad) {
        super(id, nombre, apellido, dni, fechaContratacion, salarioBase);
        this.bonificacionAntiguedad = bonificacionAntiguedad;
        this.aniosServicio = calcularAniosServicio();
    }

    private int calcularAniosServicio() {
        return LocalDate.now().getYear() - getFechaContratacion().getYear();
    }

    @Override
    public double calcularSalarioTotal() {
        return getSalarioBase() + (bonificacionAntiguedad * aniosServicio);
    }

    // Getters y setters adicionales
    public double getBonificacionAntiguedad() { return bonificacionAntiguedad; }
    public void setBonificacionAntiguedad(double bonificacionAntiguedad) { 
        this.bonificacionAntiguedad = bonificacionAntiguedad; 
    }
    
    public int getAniosServicio() { return aniosServicio; }
}
