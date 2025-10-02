import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        //Aqui ira la interfaz grafica
         Scanner lector = new Scanner(System.in);

        System.out.println("==== MÉTODO SIMPLEX ====");
        System.out.println("Este programa resuelve problemas de programación lineal.");
        System.out.print("¿Desea realizar una maximización o minimización? (escriba 'max' o 'min'): ");
        String tipo = lector.next().trim().toLowerCase();
        boolean esMinimizacion = tipo.equals("min");

        // Número de variables y restricciones
        System.out.print("Ingrese el número de variables de decisión: ");
        int numVars = lector.nextInt();

        System.out.print("Ingrese el número de restricciones: ");
        int numRestricciones = lector.nextInt();

        // Función objetivo
        double[] funcionObjetivo = new double[numVars];
        System.out.println("\nIngrese los coeficientes de la función objetivo:");
        for (int i = 0; i < numVars; i++) {
            System.out.print("Coeficiente de x" + (i + 1) + ": ");
            funcionObjetivo[i] = lector.nextDouble();
        }

    
    }
}