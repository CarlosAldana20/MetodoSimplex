class Simplex{
    //Resolver el problema usando el metodo
    public static String resolver (Problema problema){
        double[][] restricciones = problema.getRestricciones();
        double[]limites = problema.getLimites();
        double[]funcionObjetivo = problema.getFuncionObjetivo();
        boolean maximiza = problema.isMaximizacion();

        //Crear la tabla
        double[][]tabla = construirTabla (restricciones, limites, funcionObjetivo, maximiza);

        //Crear el algoritmo del metodo simplex
        while (true){
            int columnaPivot = seleccionarColumnaPivote (tabla);
            if (columnaPivot < 0 ){
                break;
            }
            int filaPivot = seleccionarFilaPivote (tabla, columnaPivot);
            if  (filaPivot < 0){
                return "No hay solucion al problema que ingresaste ";
            }
            //Realizar la operacion del pivot
            realizarPivoteo(tabla,filaPivot,columnaPivot);  
        } 
        return construirResultado(tabla, funcionObjetivo.length, maximiza);
    }
    //Construir la tabla
    private static double [][]construirTabla(double[][]restricciones, double[]limites, double[]funcionObjetivo, boolean maximiza){
        int a = restricciones.length; // Son el numero o cantidad de restricciones 
        int b = funcionObjetivo.length; //Cantidad de variables
        double[][] tabla = new double[a + 1][b + a + 1];

        for (int i = 0; i < a; i++) {
            System.arraycopy(restricciones[i], 0, tabla[i], 0, b);
            // variable de holgura
            tabla[i][b + i] = 1; 
            tabla[i][b + a] = limites[i]; 
        }
        //Funcion objetivo con signo negativoo
        for (int j = 0; j < b; j++) {
            tabla[a][j] = maximiza ? -funcionObjetivo[j] : funcionObjetivo[j];
        }

        return tabla;
    }

    //Aqui coloca la columna pivot (más negativa en la fila Z)
    private static int seleccionarColumnaPivote(double[][] tabla) {
        int filaZ = tabla.length - 1;
        int columnaPivot = -1;
        double valorMinim = 0;

        for (int j = 0; j < tabla[0].length - 1; j++) {
            if (tabla[filaZ][j] < valorMinim) {
                valorMinim = tabla[filaZ][j];
                columnaPivot = j;
            }
        }
        return columnaPivot;
    }

    // Seleccionar la fila pivot
    private static int seleccionarFilaPivote(double[][] tabla, int colPivote) {
        int a = tabla.length - 1;
        int colResultado = tabla[0].length - 1;
        int filaPivote = -1;
        double razonMin = Double.MAX_VALUE;

        for (int i = 0; i < a; i++) {
            if (tabla[i][colPivote] > 0) {
                double razon = tabla[i][colResultado] / tabla[i][colPivote];
                if (razon < razonMin) {
                    razonMin = razon;
                    filaPivote = i;
                }
            }
        }
        return filaPivote;
    }

    // Realizar operaciones en la tabla
    private static void realizarPivoteo(double[][] tabla, int filaPivote, int colPivote) {
        int columnas = tabla[0].length;
        double valorPivote = tabla[filaPivote][colPivote];

        // Actualizar la fila del pivot
        for (int j = 0; j < columnas; j++) {
            tabla[filaPivote][j] /= valorPivote;
        }

        // Convertir los valores que faltan en ceros
        for (int i = 0; i < tabla.length; i++) {
            if (i != filaPivote) {
                double factor = tabla[i][colPivote];
                for (int j = 0; j < columnas; j++) {
                    tabla[i][j] -= factor * tabla[filaPivote][j];
                }
            }
        }
    }
    private static String construirResultado(double[][] tabla, int numVariables, boolean maximiza) {
        int m = tabla.length - 1;
        int columnas = tabla[0].length;

        double[] solucion = new double[numVariables];

        for (int j = 0; j < numVariables; j++) {
            int fila = -1;
            boolean esBasica = true;

            for (int i = 0; i < m; i++) {
                if (tabla[i][j] == 1 && fila == -1) {
                    fila = i;
                } else if (tabla[i][j] != 0) {
                    esBasica = false;
                    break;
                }
            }

            if (esBasica && fila != -1) {
                solucion[j] = tabla[fila][columnas - 1];
            } else {
                solucion[j] = 0;
            }
        }

        double valorOptimo = tabla[m][columnas - 1];

        if  (!maximiza) {
        valorOptimo *= -1 ;
        }

        // Construir el resultado en texto
        StringBuilder resultado = new StringBuilder("Solución óptima encontrada:\n");
        for (int i = 0; i < numVariables; i++) {

            resultado.append("x").append(i + 1).append(" = ").append(solucion[i]).append("\n");
        }
        resultado.append("Valor Z = ").append(valorOptimo);

        //(.toString convertira a String aun e imprimira el resultado)

        return resultado.toString();
    }
}