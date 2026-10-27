import javax.swing.*;

public class Ventana extends JFrame {
    // Al escribir esto manualmente, IntelliJ asocia el componente del .form
    private JPanel panelPrincipal;

    public Ventana() {
        setTitle("Gestor de Impresiones");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // Este es el método main que le falta a tu clase para poder ejecutarse
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana v = new Ventana();
            v.setVisible(true);
        });
    }
}
