import java.util.Scanner;

public class Ejercicio3 {

    private static final double DESCUENTO_MARTES_SHOPPING = 0.30;
    private static final double DESCUENTO_MIÉRCOLES = 0.15;
    private static final double DESCUENTO_DELTA_JUEVES = 0.20;
    private static final double MONTO_MINIMO_MARTES = 20000;
    
    public static void main(String[] args) {
        DatosPago datosPago = pedirDatosDePago();
        double montoFinal = calcularMontoAPagar(
            datosPago.getMonto(),
            datosPago.getBanco(),
            datosPago.getTipoTarjeta(),
            datosPago.getDiaSemana()
        );
        System.out.printf("Monto final a pagar: %.2f%n", montoFinal);
    }

    // Solicita los datos de pago al usuario y devuelve un objeto DatosPago
    private static DatosPago pedirDatosDePago() {
        Scanner scanner = new Scanner(System.in);

        double montoGastado = obtenerDouble(scanner, "Ingrese el monto gastado por el cliente: ");
        scanner.nextLine(); // Consumir el salto de línea restante
        
        String bancoEmisor = obtenerString(scanner, "Ingrese el nombre del banco emisor: ");
        String tipoTarjeta = obtenerString(scanner, "Indique si la tarjeta es de 'CREDITO' o de 'DEBITO': ").toUpperCase();
        int diaSemana = obtenerInt(scanner, "Indique qué día de la semana es hoy (entre 0 y 6): ", 0, 6);

        return new DatosPago(montoGastado, bancoEmisor, tipoTarjeta, diaSemana);
    }

    // Calcula el monto final a pagar aplicando las promociones
    private static double calcularMontoAPagar(double montoGastado, String bancoEmisor, String tipoTarjeta, int diaSemana) {
        double descuento = 0.0;

        if (diaSemana == 2 && bancoEmisor.equals("BANCO PONI") && montoGastado > MONTO_MINIMO_MARTES) {
            descuento = DESCUENTO_MARTES_SHOPPING;
        } else if (diaSemana == 3 && tipoTarjeta.equals("CREDITO")) {
            descuento = DESCUENTO_MIÉRCOLES;
        } else if (diaSemana == 4 && tipoTarjeta.equals("DEBITO") && bancoEmisor.equals("BANCO DELTA")) {
            descuento = DESCUENTO_DELTA_JUEVES;
        }

        return montoGastado * (1 - descuento);
    }

    // Obtiene un double del usuario con validación
    private static double obtenerDouble(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar la entrada inválida
            System.out.print(mensaje);
        }
        return scanner.nextDouble();
    }

    // Obtiene un String del usuario
    private static String obtenerString(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    // Obtiene un int del usuario con validación
    private static int obtenerInt(Scanner scanner, String mensaje, int min, int max) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar la entrada inválida
            System.out.print(mensaje);
        }
        int valor = scanner.nextInt();
        if (valor < min || valor > max) {
            System.out.printf("Entrada fuera de rango. Debe estar entre %d y %d.%n", min, max);
            return obtenerInt(scanner, mensaje, min, max); // Volver a pedir la entrada
        }
        return valor;
    }
}

// Clase auxiliar para almacenar los datos de pago
class DatosPago {
    private double monto;
    private String banco;
    private String tipoTarjeta;
    private int diaSemana;

    public DatosPago(double monto, String banco, String tipoTarjeta, int diaSemana) {
        this.monto = monto;
        this.banco = banco;
        this.tipoTarjeta = tipoTarjeta;
        this.diaSemana = diaSemana;
    }

    public double getMonto() {
        return monto;
    }

    public String getBanco() {
        return banco;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public int getDiaSemana() {
        return diaSemana;
    }
}
