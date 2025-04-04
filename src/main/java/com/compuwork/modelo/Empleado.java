package com.compuwork.modelo;

import java.time.LocalDate;

public abstract class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private Departamento departamento;

    public Empleado(int id, String nombre, String apellido, String dni, LocalDate fechaContratacion, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaContratacion = fechaContratacion;
        this.salarioBase = salarioBase;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    // Método abstracto para calcular el salario total
    public abstract double calcularSalarioTotal();

    // Método para generar reporte de desempeño
    public String generarReporteDesempeno() {
        return String.format("Reporte de Desempeño - Empleado: %s %s%n" +
                           "ID: %d%n" +
                           "Departamento: %s%n" +
                           "Fecha de Contratación: %s%n" +
                           "Salario Base: %.2f%n" +
                           "Salario Total: %.2f",
                           nombre, apellido, id,
                           departamento != null ? departamento.getNombre() : "No asignado",
                           fechaContratacion,
                           salarioBase,
                           calcularSalarioTotal());
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID: %d)", nombre, apellido, id);
    }
}
