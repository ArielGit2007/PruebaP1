package ec.edu.taller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private GestorImpresiones gestor = new GestorImpresiones(10);

    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTextField codigotxt;
    private JComboBox<String> solicitantecbo;
    private JTextField documentotxt;
    private JSpinner prioridadspin;
    private JButton Registrarbtn;
    private JButton Limpiarbtn;

    public Ventana() {
        setTitle("Gestor de Impresiones");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configurarListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void configurarListeners() {
        if (Registrarbtn != null) {
            Registrarbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String codigo = (codigotxt != null) ? codigotxt.getText().trim() : "";
                    String documento = (documentotxt != null) ? documentotxt.getText().trim() : "";

                    String solicitante = "ESTUDIANTE";
                    if (solicitantecbo != null && solicitantecbo.getSelectedItem() != null) {
                        solicitante = solicitantecbo.getSelectedItem().toString();
                    }

                    int prioridad = 0;
                    if (prioridadspin != null) {
                        prioridad = (Integer) prioridadspin.getValue();
                    }

                    if (codigo.isEmpty() || documento.isEmpty()) {
                        JOptionPane.showMessageDialog(Ventana.this,
                                "Por favor, completa los campos requeridos.",
                                "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    TrabajoImpresion trabajo = new TrabajoImpresion(codigo, solicitante, documento, prioridad);
                    boolean exito = gestor.registrarTrabajo(trabajo);

                    if (exito) {
                        JOptionPane.showMessageDialog(Ventana.this,
                                "¡Trabajo registrado!\nEspacios disponibles: " + gestor.consultarEspaciosDisponibles(),
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        if (Limpiarbtn != null) Limpiarbtn.doClick();
                    } else {
                        JOptionPane.showMessageDialog(Ventana.this,
                                "No se pudo registrar. Valida el límite de capacidad o códigos duplicados.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        if (Limpiarbtn != null) {
            Limpiarbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (codigotxt != null) codigotxt.setText("");
                    if (documentotxt != null) documentotxt.setText("");
                    if (prioridadspin != null) prioridadspin.setValue(0);
                    if (solicitantecbo != null && solicitantecbo.getItemCount() > 0) {
                        solicitantecbo.setSelectedIndex(0);
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana v = new Ventana();
            v.setVisible(true);
        });
    }
}
