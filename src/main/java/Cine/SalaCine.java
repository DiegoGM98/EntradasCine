package Cine;

class SalaCine {
    private int numeroAsientos;
    private int asientosVendidos;

    public SalaCine(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
        this.asientosVendidos = 0;
    }

    // Getters
    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public int getAsientosVendidos() {
        return asientosVendidos;
    }

    // Método para vender una entrada
    public void venderEntrada() {
        asientosVendidos++;
    }

    // Método para revertir una venta de entrada
    public void reversarVenta() {
        asientosVendidos--;
    }
}
