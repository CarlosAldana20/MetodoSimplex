import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main extends JFrame {
    private JTextArea areaEntrada;
    private JTextArea areaSalida;
    private JRadioButton radioMax, radioMin;

    public main() {
        setTitle("--------Método Simplex----------");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con selección
        JPanel panelTop = new JPanel();
        radioMax = new JRadioButton("Maximización", true);
        radioMin = new JRadioButton("Minimización");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioMax);
        grupo.add(radioMin);
        panelTop.add(new JLabel("Seleccione el tipo de problema:"));
        panelTop.add(radioMax);
        panelTop.add(radioMin);

        // Área de entrada
        areaEntrada = new JTextArea(10, 50);
        areaEntrada.setBorder(BorderFactory.createTitledBorder("Ingrese datos (ejemplo en comentarios)"));
        areaEntrada.setText(
            "Ejemplo:\n" +
            "Funcion objetivo: 3 5\n" +
            "Restricciones:\n" +
            "1 2 <= 8\n" +
            "2 1 <= 10"
        );

        // Botón para resolver
        JButton btnResolver = new JButton("Resolver");
        btnResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resolverProblema();
            }
        });

        // Área de salida
        areaSalida = new JTextArea(10, 50);
        areaSalida.setEditable(false);
        areaSalida.setBorder(BorderFactory.createTitledBorder("Resultado"));

        // Agregar componentes a la ventana
        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(areaEntrada), BorderLayout.CENTER);

        // Panel inferior con botón + salida
        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(btnResolver, BorderLayout.NORTH);
        panelBottom.add(new JScrollPane(areaSalida), BorderLayout.CENTER);

        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
    
