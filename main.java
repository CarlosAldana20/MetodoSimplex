import javax.swing.*;
import java.awt.*;


public class main extends JFrame {
    txtFO = new JTextField(20);
    private JTextArea areaSalida;
    private JRadioButton radioMax, radioMin;
    tablaRest = new JTable(data, columnNames);


    public main() {
        setTitle("--------Método Simplex----------");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior 
        JPanel panelTop = new JPanel();
        radioMax = new JRadioButton("Maximización", true);
        radioMin = new JRadioButton("Minimización");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioMax);
        grupo.add(radioMin);
        panelTop.add(new JLabel("Seleccione el tipo de problema:"));
        panelTop.add(radioMax);
        panelTop.add(radioMin);

        add(panelTop, BorderLayout.NORTH);

        // entrada de los Datos
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BorderLayout());

// F.objetivo
JPanel panelFO = new JPanel();
panelFO.add(new JLabel("Función objetivo (coeficientes separados por espacios):"));
JTextField txtFO = new JTextField(20);
panelFO.add(txtFO);
panelEntrada.add(panelFO, BorderLayout.NORTH);

// Restricciones con JTable
JPanel panelRest = new JPanel(new BorderLayout());
panelRest.add(new JLabel("Restricciones (coeficientes separados por espacios + límite):"), BorderLayout.NORTH);

String[] columnNames = {"X1", "X2", "Límite"}; // Ajusta según número de variables
Object[][] data = { {"0","0","0"}, {"0","0","0"} }; // Filas iniciales
JTable tablaRest = new JTable(data, columnNames);
panelRest.add(new JScrollPane(tablaRest), BorderLayout.CENTER);

panelEntrada.add(panelRest, BorderLayout.CENTER);

add(panelEntrada, BorderLayout.CENTER);

       

        // Botón para resolver
         JPanel panelBottom = new JPanel(new BorderLayout());
        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(e -> resolverProblema());
        panelBottom.add(btnResolver, BorderLayout.NORTH);
          
        // Área de salida
        areaSalida = new JTextArea(10, 50);
        areaSalida.setEditable(false);
        areaSalida.setBorder(BorderFactory.createTitledBorder("Resultado"));
        panelBottom.add(new JScrollPane(areaSalida), BorderLayout.CENTER);

       add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
      
        private void resolverProblema() {
        try {

            // F.objetivo
            String[] partesFO = txtFO.getText().trim().split("\\s+");
            double[] funcionObjetivo = new double[partesFO.length];
            for (int i = 0; i < partesFO.length; i++) {
                funcionObjetivo[i] = Double.parseDouble(partesFO[i]);
            }

            // Restricciones
              int numRestricciones = tablaRest.getRowCount();
            double[][] restricciones = new double[numRestricciones][funcionObjetivo.length];
            double[] limites = new double[numRestricciones];

            for (int i = 0; i < numRestricciones; i++) {
                for (int j = 0; j < funcionObjetivo.length; j++) {
                    restricciones[i][j] = Double.parseDouble(tablaRest.getValueAt(i, j).toString());
                }
                limites[i] = Double.parseDouble(tablaRest.getValueAt(i, funcionObjetivo.length).toString());
            }

            boolean esMaximizacion = radioMax.isSelected();
           Problema problema = new Problema(funcionObjetivo, restricciones, limites, esMaximizacion);
            String resultado = Simplex.resolver(problema);

            areaSalida.setText(resultado);

        } catch (Exception ex) {
            areaSalida.setText("⚠ Error en los datos ingresados. Revise el formato.\n" + ex.getMessage());
            ex.printStackTrace(); }
    }

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main());
    }
}

    

    
