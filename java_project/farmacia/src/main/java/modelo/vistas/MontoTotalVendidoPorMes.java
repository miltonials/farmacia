package modelo.vistas;

/**
 *
 * @author milto
 */
public class MontoTotalVendidoPorMes {
    private String mes;
    private double montoTotal;
    
    public MontoTotalVendidoPorMes (String mes, double montoTotal) {
        this.mes = mes;
        this.montoTotal = montoTotal;
    }

    public String getMes() {
        return mes;
    }

    public double getMontoTotal() {
        return montoTotal;
    }
    
    
}
