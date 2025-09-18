import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalCuenta {
    private static ArrayList<Cuenta> cuentas = new ArrayList<>();
    private static Cuenta cuentaActual = null;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> crearCuenta();
                case 2 -> System.out.println("Cantidad total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                case 3 -> listarCuentas();
                case 4 -> seleccionarCuenta();
                case 5 -> depositar();
                case 6 -> retirar();
                case 7 -> consultarSaldo();
                case 8 -> consultarEstado();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú principal");
        System.out.println("1) Crear Cuenta");
        System.out.println("2) Conocer la cantidad de Cuentas Creadas");
        System.out.println("3) Listar Cuentas");
        System.out.println("4) Seleccionar Cuenta actual");
        System.out.println("5) Depositar");
        System.out.println("6) Retirar");
        System.out.println("7) Consultar Saldo");
        System.out.println("8) Consultar Estado (toString)");
        System.out.println("0) Salir");
    }

    private static void crearCuenta() {
        System.out.println("\n--- Crear Cuenta ---");
        System.out.println("1) Crear con nombre y saldo inicial");
        System.out.println("2) Crear solo con saldo inicial");
        int tipo = leerEntero("Seleccione tipo de creación: ");

        if (tipo == 1) {
            System.out.print("Ingrese nombre del cuenta-habiente: ");
            String nombre = sc.nextLine();
            double saldo = leerDouble("Ingrese saldo inicial: ");
            cuentas.add(new Cuenta(nombre, saldo));
        } else if (tipo == 2) {
            double saldo = leerDouble("Ingrese saldo inicial: ");
            cuentas.add(new Cuenta(saldo));
        } else {
            System.out.println("Opción inválida.");
            return;
        }
        System.out.println("Cuenta creada exitosamente.");
    }

    private static void listarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }
        System.out.println("\n--- Listado de Cuentas ---");
        for (Cuenta c : cuentas) {
            System.out.println(c.getCodCuenta() + " | Titular: " + c.toString());
        }
    }

    private static void seleccionarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
            return;
        }
        listarCuentas();
        System.out.print("Ingrese código de la cuenta a seleccionar: ");
        String cod = sc.nextLine();
        for (Cuenta c : cuentas) {
            if (c.getCodCuenta().equalsIgnoreCase(cod)) {
                cuentaActual = c;
                System.out.println("Cuenta " + cod + " seleccionada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ninguna cuenta con ese código.");
    }

    private static void depositar() {
        if (cuentaActual == null) {
            System.out.println("Primero debe seleccionar una cuenta.");
            return;
        }
        double monto = leerDouble("Ingrese monto a depositar: ");
        double nuevoSaldo = cuentaActual.depositar(monto);
        System.out.println("Depósito realizado. Nuevo saldo: " + nuevoSaldo);
    }

    private static void retirar() {
        if (cuentaActual == null) {
            System.out.println("Primero debe seleccionar una cuenta.");
            return;
        }
        double monto = leerDouble("Ingrese monto a retirar: ");
        double nuevoSaldo = cuentaActual.retirar(monto);
        System.out.println("Operación realizada. Saldo actual: " + nuevoSaldo);
    }

    private static void consultarSaldo() {
        if (cuentaActual == null) {
            System.out.println("Primero debe seleccionar una cuenta.");
            return;
        }
        System.out.println("Saldo actual de la cuenta " + cuentaActual.getCodCuenta() + ": " + cuentaActual.getSaldo());
    }

    private static void consultarEstado() {
        if (cuentaActual == null) {
            System.out.println("Primero debe seleccionar una cuenta.");
            return;
        }
        System.out.println("Estado de la cuenta:\n" + cuentaActual.toString());
    }

    // =========================
    // Métodos auxiliares
    // =========================
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
            }
        }
    }
}
