package Cine;

class Entrada {
    public enum TipoEntrada { VIP, NORMAL, IMAX }

    private TipoEntrada tipo;
    private SalaCine sala;
    private Promocion promocion;

    public Entrada(TipoEntrada tipo, SalaCine sala) {
        this.tipo = tipo;
        this.sala = sala;
    }

    // Getters y Setters
    public TipoEntrada getTipo() {
        return tipo;
    }

    public SalaCine getSala() {
        return sala;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void aplicarPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
}