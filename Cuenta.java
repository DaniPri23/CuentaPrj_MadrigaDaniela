import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cuenta {
    // =========================
    // Atributos de instancia
    // =========================
    private String codCuenta;
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;

    // =========================
    // Atributo de clase (subrayado en UML)
    // =========================
    private static int cantCuentasCreated = 0;

    // =========================
    // Constructores
    // =========================
    // Constructor con nombre y saldo
    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        inicializarCuenta(nombreCuentaHabiente, pSaldo);
    }

    // Constructor con solo saldo
    public Cuenta(double pSaldo) {
        inicializarCuenta("Sin nombre", pSaldo);
    }

    // Método privado de apoyo para inicializar
    private void inicializarCuenta(String nombre, double saldoInicial) {
        cantCuentasCreated++;
        this.codCuenta = "cta-" + cantCuentasCreated;

        this.saldo = (saldoInicial < 0) ? 0.0 : saldoInicial;
        this.nombreCuentaHabiente = (nombre == null || nombre.trim().isEmpty()) ? "Sin nombre" : nombre.trim();

        this.fechaCreacion = establecerFechaCreacion();
        this.cantDepositosRealizados = 0;
        this.cantRetirosExitososRealizados = 0;
    }

    private String establecerFechaCreacion() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formato.format(fecha);
    }

    // =========================
    // Métodos públicos
    // =========================
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        if (pNombreCuentaHabiente != null && !pNombreCuentaHabiente.trim().isEmpty()) {
            this.nombreCuentaHabiente = pNombreCuentaHabiente.trim();
        }
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            cantDepositosRealizados++;
        }
        return saldo;
    }

    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }

    private boolean validarRetiro(double monto) {
        return monto > 0 && monto <= saldo;
    }

    public static int getCantCuentasCreadas() {
        return cantCuentasCreated;
    }

    @Override
    public String toString() {
        return "Cuenta: " + codCuenta +
               " | Titular: " + nombreCuentaHabiente +
               " | Saldo: " + String.format("%.2f", saldo) +
               " | Fecha creación: " + fechaCreacion +
               " | Depósitos: " + cantDepositosRealizados +
               " | Retiros: " + cantRetirosExitososRealizados;
    }
}
