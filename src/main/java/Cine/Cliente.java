package Cine;

import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nombre;
    private String rut;
    private String correoElectronico;
    private List<Entrada> entradas;

    public Cliente(String nombre, String rut, String correoElectronico) {
        this.nombre = nombre;
        this.rut = rut;
        this.correoElectronico = correoElectronico;
        this.entradas = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    // Método para agregar una entrada
    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    // Método para eliminar una entrada
    public void eliminarEntrada(Entrada entrada) {
        entradas.remove(entrada);
    }

    // Método para contar el número de entradas
    public int contarEntradas() {
        return entradas.size();
    }
}