 //Creando la estructura del problema de IO
 class Problema{
    private double[] funcionObjetivo;
    private double[][] restricciones;
     private double[] limites; 
     private boolean esMaximizacion;
     // Constructor
    public Problema(double[] funcionObjetivo, double[][] restricciones, double[] limites, boolean esMaximizacion) {
        this.funcionObjetivo = funcionObjetivo;
        this.restricciones = restricciones;
        this.limites = limites;
        this.esMaximizacion = esMaximizacion;
    }
    //Getters
     public double[] getFuncionObjetivo() {
        return funcionObjetivo;
    }

    public double[][] getRestricciones() {
        return restricciones;
    }

    public double[] getLimites() {
        return limites;
    }

    public boolean isMaximizacion() {
        return esMaximizacion;
    }
}