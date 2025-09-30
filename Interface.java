 //Creando la estructura del problema de IO
 class Problema{
    private double[] funcionObjetivo;
    private double[][] restricciones;
     private double[] limites; 
     // Constructor
    public Problema(double[] funcionObjetivo, double[][] restricciones, double[] limites) {
        this.funcionObjetivo = funcionObjetivo;
        this.restricciones = restricciones;
        this.limites = limites;
    }//Getters
     public double[] getFuncionObjetivo() {
        return funcionObjetivo;
    }

    public double[][] getRestricciones() {
        return restricciones;
    }

    public double[] getLimites() {
        return limites;
    }
}



   //Aqui va la funcionalidad del metodo
    class Simplex{

    }



    //Aqui va la interface del programa
     class Interface{
         public static void main(String[] args) {

    }
    }

