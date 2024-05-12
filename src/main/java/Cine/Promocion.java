package Cine;

class Promocion {
    private int porcentajeDescuento;
    private String fechaVencimiento;

    public Promocion(int porcentajeDescuento, String fechaVencimiento) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaVencimiento = fechaVencimiento;
    }

    // Getters
    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
}